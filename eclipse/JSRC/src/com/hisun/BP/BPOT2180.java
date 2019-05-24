package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2180 {
    brParm BPTTLVB_BR = new brParm();
    String K_OUTPUT_FMT = "BP180";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX      ";
    String CPN_U_SUB_CBOX = "BP-U-SUB-CBOX      ";
    String CPN_P_AIZUUPIA = "AI-U-UPDATE-IA";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_AMT_CNT = 0;
    int WS_RMK_CNT = 0;
    int WS_INDEX = 0;
    int WS_CNT = 0;
    double WS_GD_AMT = 0;
    BPOT2180_WS_UUPIA_AC_NO WS_UUPIA_AC_NO = new BPOT2180_WS_UUPIA_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRTLVB BPRTLVB = new BPRTLVB();
    AICPUITM AICPUITM = new AICPUITM();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCO2180 BPCO2180 = new BPCO2180();
    SCCGWA SCCGWA;
    BPB2180_AWA_2180 BPB2180_AWA_2180;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2180 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2180_AWA_2180>");
        BPB2180_AWA_2180 = (BPB2180_AWA_2180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2180_AWA_2180.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        B030_CHECK_TELLER_FOR_CN();
        CEP.TRC(SCCGWA, "222222222222");
        B200_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "111111111111111");
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = BPB2180_AWA_2180.BR;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        S000_STARTBR_BPTTLVB();
        S000_READNEXT_BPTTLVB();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
            if (BPRTLVB.PLBOX_TP == '5') {
            } else {
                CEP.TRC(SCCGWA, "NOT DACHUNAKU");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASH_VLT;
                S000_ERR_MSG_PROC();
            }
            S000_READNEXT_BPTTLVB();
        }
        S000_ENDBR_BPTTLVB();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        WS_UUPIA_AC_NO.WS_BR = BPB2180_AWA_2180.BR;
        WS_UUPIA_AC_NO.WS_ITM_NO = BPB2180_AWA_2180.ITM_NO;
        WS_UUPIA_AC_NO.WS_SEQ = BPB2180_AWA_2180.SEQ;
        CEP.TRC(SCCGWA, BPB2180_AWA_2180.FUNC);
        if (BPB2180_AWA_2180.FUNC == '1' 
            || BPB2180_AWA_2180.FUNC == '4') {
            CEP.TRC(SCCGWA, BPB2180_AWA_2180.FUNC);
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 10 
                && BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                B020_DEPOSIT_TO_CASHBOX_PROC();
                if (BPB2180_AWA_2180.SEQ == 0) {
                    B020_SPEC_DRW_PROC();
                } else {
                    B220_01_AI_PROC();
                }
            }
        } else {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 10 
                && BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                CEP.TRC(SCCGWA, "1");
                B030_WITHDRAW_FROM_CASHBOX_PROC();
                if (BPB2180_AWA_2180.SEQ == 0) {
                    B030_SPEC_DRW_PROC();
                } else {
                    B330_01_AI_PROC();
                }
            }
        }
        B030_OUTPUT_PROC();
    }
    public void B220_01_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        WS_UUPIA_AC_NO.WS_CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_UUPIA_AC_NO);
        CEP.TRC(SCCGWA, WS_UUPIA_AC_NO);
        AICUUPIA.DATA.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICUUPIA.DATA.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.CI_NO = BPB2180_AWA_2180.CI_NO;
        AICUUPIA.DATA.DESC = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].RMK;
        CEP.TRC(SCCGWA, "2");
        S000_CALL_AIZUUPIA();
    }
    public void B330_01_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        WS_UUPIA_AC_NO.WS_CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_UUPIA_AC_NO);
        CEP.TRC(SCCGWA, WS_UUPIA_AC_NO);
        AICUUPIA.DATA.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICUUPIA.DATA.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.CI_NO = BPB2180_AWA_2180.CI_NO;
        AICUUPIA.DATA.DESC = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].RMK;
        S000_CALL_AIZUUPIA();
    }
    public void B020_DEPOSIT_TO_CASHBOX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = BPB2180_AWA_2180.BR;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "1");
        BPCUABOX.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, "2");
        BPCUABOX.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        CEP.TRC(SCCGWA, BPB2180_AWA_2180.CI_NO);
        CEP.TRC(SCCGWA, BPCUABOX.CCY);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        BPCUABOX.RMK = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].RMK;
        S000_CALL_BPZUABOX();
    }
    public void B030_WITHDRAW_FROM_CASHBOX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = BPB2180_AWA_2180.BR;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        BPCUSBOX.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        CEP.TRC(SCCGWA, BPCUSBOX.BR);
        CEP.TRC(SCCGWA, BPCUSBOX.TLR);
        CEP.TRC(SCCGWA, BPCUSBOX.CCY);
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        BPCUSBOX.RMK = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].RMK;
        S000_CALL_BPZUSBOX();
    }
    public void B020_SPEC_DRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        AICPUITM.DATA.EVENT_CODE = "CR";
        AICPUITM.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICPUITM.DATA.ITM_NO = BPB2180_AWA_2180.ITM_NO;
        AICPUITM.DATA.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.CI_NO = BPB2180_AWA_2180.CI_NO;
        S000_CALL_AIZPUITM();
    }
    public void B030_SPEC_DRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        AICPUITM.DATA.EVENT_CODE = "DR";
        AICPUITM.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        AICPUITM.DATA.ITM_NO = BPB2180_AWA_2180.ITM_NO;
        AICPUITM.DATA.AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.CI_NO = BPB2180_AWA_2180.CI_NO;
        S000_CALL_AIZPUITM();
    }
    public void B021_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = "CSADCAD";
        BPCPOEWA.DATA.BR_OLD = BPB2180_AWA_2180.BR;
        BPCPOEWA.DATA.BR_NEW = BPB2180_AWA_2180.BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
        BPCPOEWA.DATA.AC_NO = BPB2180_AWA_2180.CI_NO;
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUABOX);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_SUB_CBOX, BPCUSBOX);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO2180);
        BPCO2180.BR = BPB2180_AWA_2180.BR;
        BPCO2180.FUNC = BPB2180_AWA_2180.FUNC;
        BPCO2180.CI_NO = BPB2180_AWA_2180.CI_NO;
        BPCO2180.CI_NM = BPB2180_AWA_2180.CI_NM;
        BPCO2180.ITM_NO = BPB2180_AWA_2180.ITM_NO;
        BPCO2180.SEQ = BPB2180_AWA_2180.SEQ;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 10 
            && BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, "3");
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            BPCO2180.CCYS[WS_CCY_CNT-1].CCY = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].CCY;
            BPCO2180.CCYS[WS_CCY_CNT-1].AMT = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].AMT;
            BPCO2180.CCYS[WS_CCY_CNT-1].RMK = BPB2180_AWA_2180.CCYS[WS_CCY_CNT-1].RMK;
        }
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO2180;
        SCCFMT.DATA_LEN = 1677;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_AIZUUPIA, AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_STARTBR_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR";
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
    }
    public void S000_READNEXT_BPTTLVB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
    }
    public void S000_ENDBR_BPTTLVB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLVB_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
