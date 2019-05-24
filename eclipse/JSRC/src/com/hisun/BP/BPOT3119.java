package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3119 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP160";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_S_BV_SELL = "BP-S-BV-SELL-OUT ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_UNIT_SIGN_PROC = "DD-UNIT-SIGN-PROC   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVSO BPCSBVSO = new BPCSBVSO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3010_AWA_3010 BPB3010_AWA_3010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3119 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3010_AWA_3010>");
        BPB3010_AWA_3010 = (BPB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_FEE_INFO();
        B030_LINK_BVSO_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.PAY_TYP);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.APP_AC);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.APP_CNM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.PAY_AC);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.PAY_CNM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.CCY);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.CASH_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_CODE);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].CNM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.CNT_MTH);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.PAGE_NUM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BUY_NUM);
        if (BPB3010_AWA_3010.BV_DATA[1-1].CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_REC;
            WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3010_AWA_3010.CNT_MTH == '0' 
            && BPB3010_AWA_3010.BV_DATA[01-1].END_NO.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENDNO_CANT_INPUT;
            WS_FLD_NO = BPB3010_AWA_3010.CNT_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3010_AWA_3010.PAY_TYP != '0' 
            && BPB3010_AWA_3010.PAY_TYP != '1' 
            && BPB3010_AWA_3010.PAY_TYP != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_PAY_TYP;
            WS_FLD_NO = BPB3010_AWA_3010.PAY_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        S000_CALL_BPZFBVQU();
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
                || BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') 
            && (BPB3010_AWA_3010.CNT_MTH == '1')) {
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
            CEP.TRC(SCCGWA, WS_BVNO_LEN);
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_COMP_BEGNO == 0 
                || WS_COMP_ENDNO == 0 
                || BPB3010_AWA_3010.BV_DATA[1-1].NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].NUM_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_LINK_BVSO_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVSO);
        BPCSBVSO.PAY_TYPE = BPB3010_AWA_3010.PAY_TYP;
        BPCSBVSO.APP_AC = BPB3010_AWA_3010.APP_AC;
        BPCSBVSO.APP_NM = BPB3010_AWA_3010.APP_CNM;
        BPCSBVSO.PAY_AC = BPB3010_AWA_3010.PAY_AC;
        BPCSBVSO.PAY_NM = BPB3010_AWA_3010.PAY_CNM;
        BPCSBVSO.PAY_CCY = BPB3010_AWA_3010.CCY;
        BPCSBVSO.CASH_CD = BPB3010_AWA_3010.CASH_NO;
        BPCSBVSO.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        BPCSBVSO.CNM = BPB3010_AWA_3010.BV_DATA[1-1].CNM;
        BPCSBVSO.HEAD_NO = BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO;
        BPCSBVSO.BEG_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO;
        BPCSBVSO.END_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO;
        BPCSBVSO.NUM = BPB3010_AWA_3010.BV_DATA[1-1].NUM;
        BPCSBVSO.COUNT_MTH = BPB3010_AWA_3010.CNT_MTH;
        BPCSBVSO.PAGE_NUM = BPB3010_AWA_3010.PAGE_NUM;
        BPCSBVSO.BUY_NUM = BPB3010_AWA_3010.BUY_NUM;
        BPCSBVSO.OUTPUT_FMT = K_OUTPUT_FMT;
        S00_CALL_BPZSBVSO();
    }
    public void B020_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPB3010_AWA_3010.PAY_TYP == '1') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB3010_AWA_3010.PAY_AC;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB3010_AWA_3010.CCY;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void S00_CALL_BPZSBVSO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_SELL, BPCSBVSO);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
