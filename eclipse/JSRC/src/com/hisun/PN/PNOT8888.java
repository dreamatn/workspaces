package com.hisun.PN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT8888 {
    DBParm PNTDFPSW_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CD = "PYG09";
    int K_MTHS = 1;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String A1 = " ";
    short A2 = 0;
    short WK_B1 = 0;
    short WK_B2 = 0;
    short WK_B3 = 0;
    String WS_ERR_MSG = " ";
    int WS_VAL_EXP_EDDT = 0;
    int WS_CLCT_CNT = 0;
    char WS_CLCT_FLG = ' ';
    String PJZL = " ";
    String PJBH = " ";
    String WS_PRD_CD = " ";
    char WS_TRN_FLG = ' ';
    char WS_PAY_TYPE = ' ';
    char WS_C_T_FLG = ' ';
    String WS_CCY = " ";
    double WS_AMT = 0;
    int WS_ISS_DATE = 0;
    String WS_BV_TYP = " ";
    char WS_APP_TYPE = ' ';
    String WS_CREV_NO = " ";
    String WS_APP_AC = " ";
    String WS_PSW = " ";
    String WS_APP_NM = " ";
    String WS_APB_NO = " ";
    int WS_APB_VLDT = 0;
    String WS_PAYEE_AC = " ";
    String WS_PAYEE_NM = " ";
    String WS_PAY_PSW = " ";
    char WS_FEE_FLG = ' ';
    double WS_TXN_FEE = 0;
    double WS_BOOK_FEE = 0;
    String WS_USG_RMK = " ";
    long WS_PAY_BK = 0;
    String WS_ENCRY_NO = " ";
    String WS_BCC_CINO = " ";
    String WS_TRK_DAT2 = " ";
    String WS_TRK_DAT3 = " ";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNCI8888 PNCI8888;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PNCI8888 = new PNCI8888();
        IBS.init(SCCGWA, PNCI8888);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PNCI8888);
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNOT8888 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = "C00001";
        PNRDFPSW.KEY.BILL_NO = "20180202";
        PNRDFPSW.ENCRY_NO = "343434";
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        PNTDFPSW_RD.errhdl = true;
        IBS.WRITE(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
