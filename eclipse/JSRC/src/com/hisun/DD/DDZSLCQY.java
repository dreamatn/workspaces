package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLCQY {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_COPYBOOK = "DDCSLCQY";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_CI_NO_1 = " ";
    String WS_CI_NO_2 = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCUST CICCUST = new CICCUST();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDRCCY DDRCCY = new DDRCCY();
    DDCF552 DDCF552 = new DDCF552();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSLCQY DDCSLCQY;
    public void MP(SCCGWA SCCGWA, DDCSLCQY DDCSLCQY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLCQY = DDCSLCQY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLCQY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INPUT_CHECK();
        if (pgmRtn) return;
        B011_PSW_CHECK();
        if (pgmRtn) return;
        B012_CI_CHECK();
        if (pgmRtn) return;
        B013_ADP_PROC();
        if (pgmRtn) return;
        B020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSLCQY.FUNC);
        CEP.TRC(SCCGWA, DDCSLCQY.BV_TYP);
        CEP.TRC(SCCGWA, DDCSLCQY.AC);
        CEP.TRC(SCCGWA, DDCSLCQY.PSW_FLG);
        CEP.TRC(SCCGWA, DDCSLCQY.PSW);
        CEP.TRC(SCCGWA, DDCSLCQY.ID_TYP);
        CEP.TRC(SCCGWA, DDCSLCQY.ID_NO);
        CEP.TRC(SCCGWA, DDCSLCQY.CI_NM);
        CEP.TRC(SCCGWA, DDCSLCQY.ADP_TYPE);
        if (DDCSLCQY.FUNC != 'Y' 
            && DDCSLCQY.FUNC != 'N') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_TYPE_ERR);
        }
        if (DDCSLCQY.BV_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BV_TYP_M_INPUT);
        }
        if (DDCSLCQY.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCSLCQY.PSW_FLG == 'Y' 
            && DDCSLCQY.PSW.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_PSW_M_INPUT);
        }
        if (DDCSLCQY.ID_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_TYP_MUST_INPUT);
        }
        if (DDCSLCQY.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
        }
        if (DDCSLCQY.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NAM_MUST_INPUT);
        }
        if (DDCSLCQY.ADP_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.ADP_TYPE_M_INPUT);
        }
    }
    public void B011_PSW_CHECK() throws IOException,SQLException,Exception {
        if (DDCSLCQY.PSW_FLG == 'Y') {
            if (DDCSLCQY.BV_TYP == '1') {
                IBS.init(SCCGWA, DCCPCDCK);
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_PSW = DDCSLCQY.PSW;
                DCCPCDCK.CARD_NO = DDCSLCQY.AC;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
            if (DDCSLCQY.BV_TYP == '2') {
                IBS.init(SCCGWA, DDCIMPAY);
                DDCIMPAY.FUNC = 'K';
                DDCIMPAY.AC = DDCSLCQY.AC;
                DDCIMPAY.PSWD_OLD = DDCSLCQY.PSW;
                S000_CALL_DDZIMPAY();
                if (pgmRtn) return;
            }
        }
    }
    public void B012_CI_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDCSLCQY.AC;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_CI_NO_1 = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO_1);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'B';
        CICCUST.DATA.ID_TYPE = DDCSLCQY.ID_TYP;
        CICCUST.DATA.ID_NO = DDCSLCQY.ID_NO;
        CICCUST.DATA.CI_NM = DDCSLCQY.CI_NM;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_CI_NO_2 = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO_2);
        if (!WS_CI_NO_1.equalsIgnoreCase(WS_CI_NO_2)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.IDNM_AC_NOT_MATCH);
        }
    }
    public void B013_ADP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSLCQY.AC;
        DDRCCY.CCY = "156";
        DDRCCY.CCY_TYPE = '1';
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        if (DDCSLCQY.FUNC == 'Y') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 71 - 1) + "1" + DDRCCY.STS_WORD.substring(71 + 1 - 1);
        }
        if (DDCSLCQY.FUNC == 'N') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 71 - 1) + "0" + DDRCCY.STS_WORD.substring(71 + 1 - 1);
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DDCSLCQY.AC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DDCSLCQY.AC;
        BPCPNHIS.INFO.TX_CD = "0111552";
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 395;
        if (DDCSLCQY.FUNC == 'Y') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_TYP_CD = "I108";
        } else {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_TYP_CD = "I109";
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DDRCCY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCF552);
        DDCF552.O_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDCF552.O_TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "DD552";
        SCCFMT.DATA_PTR = DDCF552;
        SCCFMT.DATA_LEN = 20;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC,CCY,CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
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
