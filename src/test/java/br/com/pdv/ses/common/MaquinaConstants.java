package br.com.pdv.ses.common;

import br.com.pdv.ses.domain.produto.Categoria;
import br.com.pdv.ses.domain.produto.CodigoBarra;
import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.domain.produto.Setor;
import br.com.pdv.ses.domain.venda.ItemVenda;
import br.com.pdv.ses.domain.venda.Venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MaquinaConstants {

    public static int CODIGO_ZERO = 0;
    public static int CODIGO_VALIDO = 69;

    public static String EAN_13_VALIDO = "9788576356127";

    public static String NOME_VALIDO = "MAMINHA";
    public static String NOME_VAZIO = "";
    public static String NOME_ESPACO_EM_BRANCO = " ";
    public static String NOME_CURTO_DEMAIS = "GH";
    public static String NOME_LONGO_DEMAIS = "SUPER_MEGA_HYPER_ULTRA_ULTRA_HARDCORE_ULTRA_THIN_MAX_9000_PRO_TURBO_EDITION_2026_X_X_X_X_X_SPECIAL_EDITION_FINAL_VERSION_FINAL_FINAL_FINAL_APPROVED_BY_ADMIN_NO_REMAKE_250_GB_BLU_RAY_4K_HDR_10BIT_ULTRA_HD_3D_SURROUND_SOUND_LIMITED_OFFER_100_ORIGINAL_OFFICIAL_PRODUCT_SERIAL_NUMBER_999_999_999_A_B_C_D_E_F_G_H_I_J_K_L_M_N_O_P_Q_R_S_T_U_V_W_X_Y_Z";

    public static String PRECO_VALIDO = "40.69";
    public static String PRECO_ZERO = "0.00";
    public static String PRECO_VAZIO = "";
    public static String PRECO_EM_BRANCO = " ";
    public static String PRECO_NEGATIVO = "-70.89";


    public static String DESCONTO_VALIDO = "10.00";

    /**
     * domain
     */

    // validas

    public static Produto produtoValido() {
        return new Produto(
                69,
                new CodigoBarra(EAN_13_VALIDO),
                NOME_VALIDO,
                new BigDecimal(PRECO_VALIDO),
                Setor.ACOUGUE,
                Categoria.CARNES
        );
    }

    public static ItemVenda itemVendaValido() {
        return new ItemVenda(
                produtoValido(),
                2
        );
    }

    public static Venda vendaValida() {
        return new Venda(
                12,
                new ArrayList<ItemVenda>(
                )
        );
    }

    public static Produto produtoInvalido() {
        return new Produto(
                0,
                new CodigoBarra(EAN_13_VALIDO),
                NOME_LONGO_DEMAIS,
                new BigDecimal(PRECO_NEGATIVO),
                Setor.ACOUGUE,
                Categoria.CARNES
        );
    }

    public static ItemVenda itemVendaInvalido() {
        return new ItemVenda(
                produtoInvalido(),
                2
        );
    }
}
