package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1317 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_DEC_MP0 = 0;
    double WS_DEC_MP1 = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_MREM = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    FXCSBFXT FXCSBFXT = new FXCSBFXT();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPRPBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    FXB1310_AWA_1310 FXB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1317 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPRPBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1310_AWA_1310>");
        FXB1310_AWA_1310 = (FXB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_APRV_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.TIK_NO);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.EX_RATE);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.SPREAD);
        if (FXB1310_AWA_1310.TIK_NO.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_NO_ERR);
        }
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.EX_CODE);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.EX_GROUP);
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = FXB1310_AWA_1310.EX_GROUP;
        if (FXB1310_AWA_1310.EX_CODE == null) FXB1310_AWA_1310.EX_CODE = "";
        JIBS_tmp_int = FXB1310_AWA_1310.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) FXB1310_AWA_1310.EX_CODE += " ";
        BPREXRD.KEY.CCY = FXB1310_AWA_1310.EX_CODE.substring(0, 3);
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (BPCTEXRM.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE);
        }
        CEP.TRC(SCCGWA, BPREXRD.EXR_PNT);
        CEP.TRC(SCCGWA, BPREXRD.EXR_RND);
        if (BPREXRD.EXR_PNT == '2') {
            WS_DEC_MREM = FXB1310_AWA_1310.EX_RATE % 1;
            WS_DEC_MP2 = (FXB1310_AWA_1310.EX_RATE - WS_DEC_MREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_MP2 += .01;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_MREM >= .005) {
                    WS_DEC_MP2 += .01;
                }
            }
            CEP.TRC(SCCGWA, "PNT-2-RATE");
        } else if (BPREXRD.EXR_PNT == '3') {
            WS_DEC_MREM = FXB1310_AWA_1310.EX_RATE % 1;
            WS_DEC_MP3 = (FXB1310_AWA_1310.EX_RATE - WS_DEC_MREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_MP3 += .001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_MREM >= .0005) {
                    WS_DEC_MP3 += .001;
                }
            }
            CEP.TRC(SCCGWA, "PNT-3-RATE");
        } else if (BPREXRD.EXR_PNT == '4') {
            WS_DEC_MREM = FXB1310_AWA_1310.EX_RATE % 1;
            WS_DEC_MP4 = (FXB1310_AWA_1310.EX_RATE - WS_DEC_MREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_MP4 += .0001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_MREM >= .00005) {
                    WS_DEC_MP4 += .0001;
                }
            }
            CEP.TRC(SCCGWA, "PNT-4-RATE");
        } else {
        }
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.EX_RATE);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.STATUS);
        if (FXB1310_AWA_1310.STATUS != 'P') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TICKET_STATUS_ERR);
        }
    }
    public void B020_ADD_APRV_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSBFXT);
        FXCSBFXT.KEY.TIK_NO = FXB1310_AWA_1310.TIK_NO;
        FXCSBFXT.RMK = FXB1310_AWA_1310.RMK;
        FXCSBFXT.SPREAD = FXB1310_AWA_1310.SPREAD;
        FXCSBFXT.EX_RATE = FXB1310_AWA_1310.EX_RATE;
        FXCSBFXT.BUY_AMT = FXB1310_AWA_1310.BUY_AMT;
        FXCSBFXT.SELL_AMT = FXB1310_AWA_1310.SELL_AMT;
        FXCSBFXT.FUNC = 'F';
        S000_CALL_FXZSBFXT();
    }
    public void S000_CALL_FXZSBFXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-BFXT", FXCSBFXT);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
