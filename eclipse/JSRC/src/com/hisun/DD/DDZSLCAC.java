package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLCAC {
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_C_INF = "DD906";
    short K_MAX_ROW = 20;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_O = 0;
    short WS_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_CNT = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    short WS_NUM1 = 0;
    char WS_DDTCCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICACCU CICACCU = new CICACCU();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDCOLCAC DDCOLCAC = new DDCOLCAC();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSLCAC DDCSLCAC;
    public void MP(SCCGWA SCCGWA, DDCSLCAC DDCSLCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLCAC = DDCSLCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLCAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "22222");
        CEP.TRC(SCCGWA, DDCSLCAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSLCAC.AC_NO);
        CEP.TRC(SCCGWA, DDCSLCAC.START_DT);
        CEP.TRC(SCCGWA, DDCSLCAC.END_DT);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACCOUNT_LIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSLCAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSLCAC.AC_NO);
        CEP.TRC(SCCGWA, DDCSLCAC.START_DT);
        CEP.TRC(SCCGWA, DDCSLCAC.END_DT);
        if (DDCSLCAC.CI_NO.trim().length() == 0 
            && DDCSLCAC.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_AC_MUST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSLCAC.START_DT == 0) {
            DDCSLCAC.START_DT = 101;
        }
        if (DDCSLCAC.END_DT == 0) {
            DDCSLCAC.END_DT = 99991231;
        }
    }
    public void B020_GET_ACCOUNT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "33333");
        if (DDCSLCAC.AC_NO.trim().length() > 0) {
            B021_GET_ACCOUNT_BY_AC();
            if (pgmRtn) return;
        } else {
            B022_GET_ACCOUNT_BY_CI();
            if (pgmRtn) return;
        }
    }
    public void B021_GET_ACCOUNT_BY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLCAC.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        R000_OUTPUT_INI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = DDCSLCAC.AC_NO;
            S000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            S000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            while (WS_DDTCCY_FLG != 'N') {
                CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DDRCCY.CUS_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
                IBS.init(SCCGWA, BPCPOCAC);
                BPCPOCAC.INFO.FUNC = 'I';
                BPCPOCAC.DATA_INFO.AC = DDRCCY.CUS_AC;
                BPCPOCAC.DATA_INFO.ACO_AC = DDRCCY.KEY.AC;
                BPCPOCAC.DATA_INFO.STS = 'N';
                S000_CALL_BPZPOCAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CHNL_NO);
                R000_DATA_OUTPUT_LIST();
                if (pgmRtn) return;
                S000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
            S000_ENDBR_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B022_GET_ACCOUNT_BY_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        IBS.init(SCCGWA, DDCOLCAC);
        CICQCIAC.FUNC = '5';
        CICQCIAC.DATA.CI_NO = DDCSLCAC.CI_NO;
        CICQCIAC.DATA.FRM_APP = "DD";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        R000_OUTPUT_INI();
        if (pgmRtn) return;
        for (WS_I = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO.trim().length() != 0 
            && WS_I <= 99; WS_I += 1) {
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO);
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
                S000_STARTBR_DDTCCY();
                if (pgmRtn) return;
                S000_READNEXT_DDTCCY();
                if (pgmRtn) return;
                while (WS_DDTCCY_FLG != 'N') {
                    CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = DDRCCY.CUS_AC;
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                    CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
                    IBS.init(SCCGWA, BPCPOCAC);
                    BPCPOCAC.INFO.FUNC = 'I';
                    BPCPOCAC.DATA_INFO.AC = DDRCCY.CUS_AC;
                    BPCPOCAC.DATA_INFO.ACO_AC = DDRCCY.KEY.AC;
                    BPCPOCAC.DATA_INFO.STS = 'N';
                    S000_CALL_BPZPOCAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CHNL_NO);
                    R000_DATA_OUTPUT_LIST();
                    if (pgmRtn) return;
                    S000_READNEXT_DDTCCY();
                    if (pgmRtn) return;
                }
                S000_ENDBR_DDTCCY();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_DATA_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOLCAC);
        DDCOLCAC.AC_INFO.CI_NO = CICACCU.DATA.CI_NO;
        DDCOLCAC.AC_INFO.AC_CNM = CICACCU.DATA.AC_CNM;
        DDCOLCAC.AC_INFO.AC_TYPE = DDRMST.AC_TYPE;
        DDCOLCAC.AC_INFO.AC_NO = DDRMST.KEY.CUS_AC;
        DDCOLCAC.AC_INFO.OPEN_DT = DDRMST.OPEN_DATE;
        DDCOLCAC.AC_INFO.OPEN_BR = DDRMST.OPEN_DP;
        DDCOLCAC.AC_INFO.OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
        DDCOLCAC.AC_INFO.CCY = DDRCCY.CCY;
        DDCOLCAC.AC_INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        DDCOLCAC.AC_INFO.CCY_FLG = DDRMST.CCY_FLG;
        CEP.TRC(SCCGWA, DDCOLCAC.AC_INFO.CCY);
        CEP.TRC(SCCGWA, DDCOLCAC.AC_INFO.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOLCAC.AC_INFO.CCY_FLG);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOLCAC);
        SCCMPAG.DATA_LEN = 322;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 322;
        SCCMPAG.SCR_ROW_CNT = K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC "
            + "AND OPEN_DATE >= :DDCSLCAC.START_DT "
            + "AND OPEN_DATE <= :DDCSLCAC.END_DT "
            + "AND CI_TYP < > '1' "
            + "AND AC_STS < > 'C'";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void S000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.order = "AC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void S000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void S000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_FLG = 'Y';
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC, true);
        if (BPCPOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOCAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
