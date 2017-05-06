/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author agostinho
 */
public class PasswordUtil {

    private Cipher cipher;
	private byte[] encryptKey;
	private KeySpec keySpec;
	private SecretKeyFactory secretKeyFactory;
	private SecretKey secretKey;

	/**
	 * Method that create a new instance of class.
	 * @return
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static PasswordUtil newInstance() throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {

		return new PasswordUtil();
	}

	/**
	 * Default Constructor.
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 */
	private PasswordUtil() throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {

		String key = "http://www.rodrigolazoti.com.br";

		encryptKey = key.getBytes( "UTF-8" );
		cipher = Cipher.getInstance( "DESede" );
		keySpec = new DESedeKeySpec( encryptKey );
		secretKeyFactory = SecretKeyFactory.getInstance( "DESede" );
		secretKey = secretKeyFactory.generateSecret( keySpec );
	}

	/**
	 * Method that encrypts a value.
	 * @param value
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt( String value ) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		cipher.init( Cipher.ENCRYPT_MODE, secretKey );

		byte[] cipherText = cipher.doFinal( value.getBytes( "UTF-8" ) );
		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode( cipherText );
	}

	/**
	 * Methot that decrypts a value.
	 * @param value
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public String decrypt( String value ) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			IOException {

		cipher.init( Cipher.DECRYPT_MODE, secretKey );

		BASE64Decoder dec = new BASE64Decoder();
		byte[] decipherText = cipher.doFinal( dec.decodeBuffer( value ) );

		return new String( decipherText );
	}
}
