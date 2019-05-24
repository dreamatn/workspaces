package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSPVS {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITACAC_RD;
    brParm CITACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    int WS_I = 0;
    int WS_II = 0;
    char WS_DD = ' ';
    char WS_TD = ' ';
    String WS_AGR_NO = " ";
    double WS_TOTAL_BAL = 0;
    char WS_END_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    TDCBKBAL TDCBKBAL = new TDCBKBAL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCSBFJQ DDCSBFJQ = new DDCSBFJQ();
    CICOPVS CICOPVS = new CICOPVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSPVS CICSPVS;
    public void MP(SCCGWA SCCGWA, CICSPVS CICSPVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSPVS = CICSPVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSPVS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPVS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSPVS.DATA.AGR_NO.trim().length() > 0) {
            B020_INQ_PVS_INF_BY_AC();
            if (pgmRtn) return;
        } else if (CICSPVS.DATA.CI_NO.trim().length() > 0) {
            B030_INQ_PVS_INF_BY_CI();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_PVS_INF_BY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICSPVS.DATA.AGR_NO;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        if (CIRACR.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("0")) {
            CEP.TRC(SCCGWA, "该账号不是备付金账号");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_IS_NOT_PVS_AC, CICSPVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCSBFJQ);
        DDCSBFJQ.FUNC = 'B';
        DDCSBFJQ.CI_NO = CIRACR.CI_NO;
        S000_CALL_DDZSBFJQ();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_02_OUT_DATA_DD();
        if (pgmRtn) return;
    }
    public void B030_INQ_PVS_INF_BY_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSPVS.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICSPVS.DATA.CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "客户下无备付金账�?");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PVS_AC_NOT_EXIST, CICSPVS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCSBFJQ);
        DDCSBFJQ.FUNC = 'B';
        DDCSBFJQ.CI_NO = CIRBAS.KEY.CI_NO;
        S000_CALL_DDZSBFJQ();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            R000_02_OUT_DATA_DD();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        CICSPVS.OUT_DATA.TOTAL_BAL = WS_TOTAL_BAL;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_DATA_DD() throws IOException,SQLException,Exception {
        WS_I = WS_I + 1;
        IBS.init(SCCGWA, CICOPVS);
        CICOPVS.DATA.COUNT = WS_I;
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        T000_READ_CITACAC_DEFAULT();
        if (pgmRtn) return;
        CICOPVS.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOPVS.DATA.ACAC_NO = CIRACAC.KEY.ACAC_NO;
        CICOPVS.DATA.CI_NO = CIRACR.CI_NO;
        CICOPVS.DATA.AC_CNM = CIRACR.AC_CNM;
        CICOPVS.DATA.FRM_APP = "DD";
        CICOPVS.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
        CICOPVS.DATA.PVE_TYPE = CICSPVS.DATA.PVS_TYPE;
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = CIRACR.KEY.AGR_NO;
        DDCIQBAL.DATA.CCY = CIRACAC.CCY;
        DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CICOPVS.DATA.BAL = DDCIQBAL.DATA.CURR_BAL;
        WS_TOTAL_BAL = WS_TOTAL_BAL + DDCIQBAL.DATA.CURR_BAL;
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = CIRACR.KEY.AGR_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.SPC_KIND);
        CICOPVS.DATA.SPC_KIND = DDCIMMST.DATA.SPC_KIND;
        CICOPVS.DATA.BASE_AMT = DDCSBFJQ.BASE_LMT;
        CICOPVS.DATA.SINAGL_AMT = DDCSBFJQ.S_LMT;
        CICOPVS.DATA.DAY_AMT_LMT = DDCSBFJQ.DAMT_LMT;
        CICOPVS.DATA.MON_AMT_LMT = DDCSBFJQ.MAMT_LMT;
        CICOPVS.DATA.DAY_CNT_LMT = DDCSBFJQ.DCNT_LMT;
        CICOPVS.DATA.MON_CNT_LMT = DDCSBFJQ.MCNT_LMT;
        if ((CICSPVS.DATA.FRM_APP.equalsIgnoreCase("DD") 
            || CICSPVS.DATA.FRM_APP.trim().length() == 0) 
            && (CICSPVS.DATA.SPC_KIND.equalsIgnoreCase(CICOPVS.DATA.SPC_KIND) 
            || CICSPVS.DATA.SPC_KIND.trim().length() == 0)) {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOPVS);
            SCCMPAG.DATA_LEN = 464;
            B_MPAG();
            if (pgmRtn) return;
        }
        R000_03_OUT_DATA_TD();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_DATA_TD() throws IOException,SQLException,Exception {
        WS_II = 0;
        WS_AGR_NO = CIRACR.KEY.AGR_NO;
        IBS.init(SCCGWA, TDCBKBAL);
        TDCBKBAL.OPP_AC_NO = WS_AGR_NO;
        S000_CALL_TDZBKBAL();
        if (pgmRtn) return;
        WS_II = WS_II + 1;
        CEP.TRC(SCCGWA, TDCBKBAL.ITEM[WS_II-1].ACO_AC);
        while (TDCBKBAL.ITEM[WS_II-1].ACO_AC.trim().length() != 0 
            && SCCMPAG.FUNC != 'E' 
            && WS_END_FLG != 'Y') {
            CEP.TRC(SCCGWA, WS_II);
            CEP.TRC(SCCGWA, TDCBKBAL.ITEM[WS_II-1].ACO_AC);
            WS_I = WS_I + 1;
            IBS.init(SCCGWA, CICOPVS);
            CICOPVS.DATA.COUNT = WS_I;
            CICOPVS.DATA.AGR_NO = TDCBKBAL.ITEM[WS_II-1].AC_NO;
            CICOPVS.DATA.ACAC_NO = TDCBKBAL.ITEM[WS_II-1].ACO_AC;
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = TDCBKBAL.ITEM[WS_II-1].AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICOPVS.DATA.CI_NO = CIRACR.CI_NO;
            CICOPVS.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOPVS.DATA.FRM_APP = "TD";
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.KEY.ACAC_NO = TDCBKBAL.ITEM[WS_II-1].ACO_AC;
            T000_READ_CITACAC();
            if (pgmRtn) return;
            CICOPVS.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
            CICOPVS.DATA.BV_NO = CIRACAC.BV_NO;
            CICOPVS.DATA.PVE_TYPE = CICSPVS.DATA.PVS_TYPE;
            CEP.TRC(SCCGWA, TDCBKBAL.ITEM[WS_II-1].BAL);
            CICOPVS.DATA.BAL = TDCBKBAL.ITEM[WS_II-1].BAL;
            WS_TOTAL_BAL = WS_TOTAL_BAL + TDCBKBAL.ITEM[WS_II-1].BAL;
            CICOPVS.DATA.SPC_KIND = DDCIMMST.DATA.SPC_KIND;
            CICOPVS.DATA.BASE_AMT = DDCSBFJQ.BASE_LMT;
            CICOPVS.DATA.SINAGL_AMT = DDCSBFJQ.S_LMT;
            CICOPVS.DATA.DAY_AMT_LMT = DDCSBFJQ.DAMT_LMT;
            CICOPVS.DATA.MON_AMT_LMT = DDCSBFJQ.MAMT_LMT;
            CICOPVS.DATA.DAY_CNT_LMT = DDCSBFJQ.DCNT_LMT;
            CICOPVS.DATA.MON_CNT_LMT = DDCSBFJQ.MCNT_LMT;
            if ((CICSPVS.DATA.FRM_APP.equalsIgnoreCase("TD") 
                || CICSPVS.DATA.FRM_APP.trim().length() == 0) 
                && (CICSPVS.DATA.SPC_KIND.equalsIgnoreCase(CICOPVS.DATA.SPC_KIND) 
                || CICSPVS.DATA.SPC_KIND.trim().length() == 0)) {
                CEP.TRC(SCCGWA, "AAA");
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOPVS);
                SCCMPAG.DATA_LEN = 464;
                B_MPAG();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, TDCBKBAL.END_FLG);
            if (WS_II == 20) {
                if (TDCBKBAL.END_FLG != 'Y') {
                    WS_II = 1;
                    S000_CALL_TDZBKBAL();
                    if (pgmRtn) return;
                } else {
                    WS_END_FLG = TDCBKBAL.END_FLG;
                }
            } else {
                WS_II = WS_II + 1;
            }
        }
    }
    public void S000_CALL_TDZBKBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BK-BAL", TDCBKBAL, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST, true);
    }
    public void S000_CALL_DDZSBFJQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSBFJQ", DDCSBFJQ, true);
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSPVS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSPVS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "SUBSTR ( STSW , 6 , 1 ) = '1' "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
