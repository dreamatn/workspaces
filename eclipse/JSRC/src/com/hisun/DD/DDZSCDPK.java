package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCDPK {
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD625";
    String K_HIS_REMARKS = "DORMANT ACCOUNT CLOSE";
    String K_HIS_MMO = "GAA";
    String K_CHK_STS_TBL = "0005";
    String K_APP_MMO = "DD";
    String K_ITM_NO = " ";
    String WS_ERR_MSG = " ";
    char WS_MST_AC_STS = ' ';
    double WS_DREG_BAL = 0;
    char WS_DREG_STS = ' ';
    char WS_DREG_RCD_STS = ' ';
    String WS_AC_AC = " ";
    DDZSCDPK_WS_OUT_DATA WS_OUT_DATA = new DDZSCDPK_WS_OUT_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDCRMST DDCRMST = new DDCRMST();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    DDCSCDPK DDCSCDPK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCDPK DDCSCDPK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCDPK = DDCSCDPK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCDPK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_MPAGE_OUTPUT_START();
        if (pgmRtn) return;
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B020_GET_DDAC_INF_PROC();
        if (pgmRtn) return;
        B030_GET_DCAC_PROC();
        if (pgmRtn) return;
    }
    public void B000_MPAGE_OUTPUT_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 395;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DDCSCDPK.AC_NO.trim().length() == 0 
            && DDCSCDPK.ID_TYP.trim().length() == 0 
            && DDCSCDPK.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CAN_NOT_ALL_SPACE);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DDCSCDPK.ID_TYP.trim().length() == 0 
            && DDCSCDPK.ID_NO.trim().length() > 0) 
            || (DDCSCDPK.ID_TYP.trim().length() > 0 
            && DDCSCDPK.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        if (DDCSCDPK.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = DDCSCDPK.AC_NO;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_AC_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            WS_OUT_DATA.WS_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_OUT_DATA.WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_OUT_DATA.WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_ID_TYP);
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_ID_NO);
        }
    }
    public void B020_GET_DDAC_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSCDPK.AC_NO;
            DDCRMST.FUNC = 'R';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
            if (DDRMST.CARD_FLG == 'Y' 
                || DDRMST.CARD_FLG == 'K') {
                WS_OUT_DATA.WS_OPEN_DATE = DDRMST.OPEN_DATE;
                WS_OUT_DATA.WS_OPEN_BR = DDRMST.OPEN_DP;
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.KEY.AC = WS_AC_AC;
                DDTCCY_RD = new DBParm();
                DDTCCY_RD.TableName = "DDTCCY";
                IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH1_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_OUT_DATA.WS_CCY = DDRCCY.CCY;
                WS_OUT_DATA.WS_AC_BAL = DDRCCY.CURR_BAL;
                B090_OUTPUT_DATA_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_DCAC_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.REL_AC_NO = DDCSCDPK.AC_NO;
            CICQACRL.DATA.AC_REL = "09";
            CICQACRL.FUNC = '4';
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = CICQACRL.O_DATA.O_AC_NO;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_AC_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            WS_OUT_DATA.WS_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_OUT_DATA.WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_OUT_DATA.WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_AC_NO;
                DDCRMST.FUNC = 'R';
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                if (pgmRtn) return;
                if (DDRMST.CARD_FLG == 'Y' 
                    || DDRMST.CARD_FLG == 'K') {
                    WS_OUT_DATA.WS_OPEN_DATE = DDRMST.OPEN_DATE;
                    WS_OUT_DATA.WS_OPEN_BR = DDRMST.OPEN_DP;
                    IBS.init(SCCGWA, DDRCCY);
                    DDRCCY.KEY.AC = WS_AC_AC;
                    DDTCCY_RD = new DBParm();
                    DDTCCY_RD.TableName = "DDTCCY";
                    IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH1_REC_NOTFND;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    WS_OUT_DATA.WS_CCY = DDRCCY.CCY;
                    WS_OUT_DATA.WS_AC_BAL = DDRCCY.CURR_BAL;
                    B090_OUTPUT_DATA_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B090_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_AC_NO = DDCSCDPK.AC_NO;
        WS_OUT_DATA.WS_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_OUT_DATA.WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        WS_OUT_DATA.WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        WS_OUT_DATA.WS_CCY = DDRCCY.CCY;
        WS_OUT_DATA.WS_AC_BAL = DDRCCY.CURR_BAL;
        WS_OUT_DATA.WS_OPEN_BR = DDRMST.OPEN_DP;
        WS_OUT_DATA.WS_OPEN_DATE = DDRMST.OPEN_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        SCCMPAG.DATA_LEN = 395;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
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
