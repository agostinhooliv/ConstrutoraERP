
import br.com.construtora.dao.financeiro.DespesasGeralDAO;
import br.com.construtora.dao.financeiro.DespesasObraDAO;
import br.com.construtora.model.Obra;
import br.com.construtora.model.financeiro.Despesas;
import br.com.construtora.model.financeiro.DespesasGeral;
import br.com.construtora.model.financeiro.DespesasObra;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agostinho
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* Despesas desp = new Despesas();
        desp.setDtDesp(new Date());
        new DespesasDAO().salvar(desp);


        System.out.println(new DespesasDAO().findByDtDesp(new Date()));

        DespesasObra dO = new DespesasObra();
        Obra o = new ObraDAO().findByDeObra("Milos I");
        dO.setIdObra(o);

        dO.setIdDespesa(desp);

        //new DespesasObraDAO().salvar(dO);

        System.out.println("");*/

 /*Geral g = new Geral();
        g.setDtDesp(new Date());
        g.setDtVenc(new Date());
        g.setTpDesp("Água");
        g.setVrDesp(new BigDecimal("22.30"));
        new GeralDAO().salvar(g);
        System.out.println(new GeralDAO().findByDtDesp("Água").getVrDesp());


        MaterialObra mo = new MaterialObra();
        mo.setDtDesp(new Date());
        mo.setTpMate(TipoMaterialEnum.Brita.toString());
        mo.setDtDesp(new Date());
        mo.setDeMate("23 m3");
        mo.setVrUnit(new BigDecimal("700.00"));
        mo.setQtdUnit(1);
        mo.setVrDesc(new BigDecimal("0.00"));
        mo.setVrTota(new BigDecimal((mo.getVrUnit().doubleValue() * mo.getQtdUnit()) - mo.getVrDesc().doubleValue()));
        mo.setNrNotaFisc(111111);
        Fornecedor forn = new FornecedorDAO().findByCNPJ("12476348000139");

        mo.setFornecedor(forn);        
        new MaterialObraDAO().salvar(mo);

        for (MaterialObra mo2 : new MaterialObraDAO().findAll()) {
        System.out.println("Descrição: " + mo2.getDeMate());
        }*/
//        Pessoal pess = new Pessoal();
//        pess.setDtDesp(new Date());
//        pess.setFuncionario(new FuncionarioDAO().findByCPF("12323212233"));
//        pess.setVrNorm(new BigDecimal("1600.25"));
//        pess.setVrFgts(new BigDecimal("120.00"));
//        pess.setVrInss(new BigDecimal(50.00));
//        pess.setDescVrInss(new BigDecimal(40.00));
//        //pess.setVrTickTrans(new BigDecimal(100));
//        //pess.setDescTickTrans(new BigDecimal(20));
//        // pess.setVrAssiMedi(new BigDecimal(100));
//        // pess.setDescAssiMedi(new BigDecimal(10));
//        pess.setVrTickAlim(new BigDecimal(100));
//        pess.setDescTickAlim(new BigDecimal(10));
//        pess.setDescContSindi(new BigDecimal(10));
//        pess.setVrLiqu(new BigDecimal(pess.getVrNorm().doubleValue() - pess.getDescVrInss().doubleValue()));
//        pess.setVrTotaPago(new BigDecimal(pess.getVrLiqu().doubleValue() + pess.getVrFgts().doubleValue() + pess.getVrInss().doubleValue()));
//
//        //Calcula o valor do salário líquido e valor total pago incluindo o valor/incluindo o desconto do vale transporte, se o func tiver feito a opção por este
//        if (pess.getVrTickTrans() != null) {
//            pess.setVrLiqu(new BigDecimal(pess.getVrLiqu().doubleValue() - pess.getDescTickTrans().doubleValue()));
//            //Ajusta o novo valor líquido
//            pess.setVrTotaPago(new BigDecimal(pess.getVrLiqu().doubleValue() + pess.getVrFgts().doubleValue() + pess.getVrInss().doubleValue()));
//            pess.setVrTotaPago(new BigDecimal(pess.getVrTotaPago().doubleValue() + pess.getVrTickTrans().doubleValue()));
//        }
//        //Calcula o valor do salário líquido e valor total pago incluindo o valor/incluindo o desconto do ticket alimentacao, se o func tiver feito a opção por este
//        if (pess.getVrTickAlim() != null) {
//            pess.setVrLiqu(new BigDecimal(pess.getVrLiqu().doubleValue() - pess.getDescTickAlim().doubleValue()));
//            //Ajusta o novo valor líquido
//            pess.setVrTotaPago(new BigDecimal(pess.getVrLiqu().doubleValue() + pess.getVrFgts().doubleValue() + pess.getVrInss().doubleValue()));
//            pess.setVrTotaPago(new BigDecimal(pess.getVrTotaPago().doubleValue() + pess.getVrTickAlim().doubleValue()));
//        }
//        //Calcula o valor do salário líquido e valor total pago incluindo o valor/desconto da assistência médica, se o func tiver feito a opção por este
//        if (pess.getVrAssiMedi() != null) {
//            pess.setVrLiqu(new BigDecimal(pess.getVrLiqu().doubleValue() - pess.getDescAssiMedi().doubleValue()));
//            //Ajusta o novo valor líquido
//            pess.setVrTotaPago(new BigDecimal(pess.getVrLiqu().doubleValue() + pess.getVrFgts().doubleValue() + pess.getVrInss().doubleValue()));
//            pess.setVrTotaPago(new BigDecimal(pess.getVrTotaPago().doubleValue() + pess.getVrAssiMedi().doubleValue()));
//        }
//        //Calcula o valor do salário líquido incluindo o desconto da contribuição sindical quando houver, se o func tiver feito a opção por este
//        if (pess.getDescContSindi() != null) {
//            pess.setVrLiqu(new BigDecimal(pess.getVrLiqu().doubleValue() - pess.getDescContSindi().doubleValue()));
//        }
//
//        new PessoalDAO().salvar(pess);
        DespesasGeral despesasGeral = new DespesasGeral();
        despesasGeral.setDtDesp(new Date());
        despesasGeral.setSgStat('A');        
        despesasGeral.setDtVenc(new Date());
        despesasGeral.setTpDesp("DESPACHANTE");
        despesasGeral.setVrDesp(500.00);
        DespesasGeralDAO despesasGeralDAO = new DespesasGeralDAO();
        despesasGeralDAO.salvar(despesasGeral);
        DespesasObra despesasObra = new DespesasObra();
        despesasObra.setDespesasidDespesas(new Despesas(despesasGeral.getIdDespesas()));
        despesasObra.setObraidObra(new Obra(1));
        DespesasObraDAO despesasObraDAO = new DespesasObraDAO();
        despesasObraDAO.salvar(despesasObra);
    }
}
