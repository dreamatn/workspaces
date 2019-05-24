package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5081 {
    BigDecimal bigD;
    String CPN_S_EXPT = "BP-DEF-EXPT      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_MREM = 0;
    double WS_SP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCEXPT BPCEXPT = new BPCEXPT();
    BPCIFQ BPCIFQ = new BPCIFQ();
    BPCDERT BPCDERT = new BPCDERT();
    BPCMT BPCMT = new BPCMT();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5081_AWA_5081 BPB5081_AWA_5081;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5081 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5081_AWA_5081>");
        BPB5081_AWA_5081 = (BPB5081_AWA_5081) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.BR);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.EXR_TYP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.CCY);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.CMP_FLG);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.HCS_BP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.HFX_BP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.HCS_SP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.HFX_SP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.CS_BP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.FX_BP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.CS_SP);
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.FX_SP);
        if (BPB5081_AWA_5081.BR == 706660800) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BRANCH_ERR);
        }
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5081_AWA_5081.EXR_TYP;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE);
        }
        WS_CCY = BPB5081_AWA_5081.CCY;
        WS_FLD_NO = BPB5081_AWA_5081.CCY_NO;
        R000_CHECK_CCY();
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        if (BPREXRT.DAT_TXT.BASE_CCY.equalsIgnoreCase(BPB5081_AWA_5081.CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_NOT_DEF_EX_CCY;
            WS_FLD_NO = BPB5081_AWA_5081.CCY_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB5081_AWA_5081.CMP_FLG);
        if (BPB5081_AWA_5081.CMP_FLG != '0' 
            && BPB5081_AWA_5081.CMP_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COM_FLG_ERR;
            WS_FLD_NO = BPB5081_AWA_5081.CMP_FLG_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXPT);
        BPCEXPT.FUNC = 'A';
        BPCEXPT.BR = BPB5081_AWA_5081.BR;
        BPCEXPT.EXR_TYP = BPB5081_AWA_5081.EXR_TYP;
        BPCEXPT.CCY = BPB5081_AWA_5081.CCY;
        BPCEXPT.CMP_FLG = BPB5081_AWA_5081.CMP_FLG;
        BPCEXPT.HCS_BP = BPB5081_AWA_5081.HCS_BP;
        BPCEXPT.HFX_BP = BPB5081_AWA_5081.HFX_BP;
        BPCEXPT.HCS_SP = BPB5081_AWA_5081.HCS_SP;
        BPCEXPT.HFX_SP = BPB5081_AWA_5081.HFX_SP;
        BPCEXPT.CS_BP = BPB5081_AWA_5081.CS_BP;
        BPCEXPT.FX_BP = BPB5081_AWA_5081.FX_BP;
        BPCEXPT.CS_SP = BPB5081_AWA_5081.CS_SP;
        BPCEXPT.FX_SP = BPB5081_AWA_5081.FX_SP;
        S000_CALL_BPZEXPT();
        B030_UPD_BPTTQP();
    }
    public void B030_UPD_BPTTQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFQ);
        BPCIFQ.DATA.EXR_TYPE = BPB5081_AWA_5081.EXR_TYP;
        BPCIFQ.DATA.BR = 706660800;
        BPCIFQ.DATA.CCY = BPB5081_AWA_5081.CCY;
        if (BPB5081_AWA_5081.EXR_TYP.equalsIgnoreCase("TRE") 
            || BPB5081_AWA_5081.EXR_TYP.equalsIgnoreCase("MDR")) {
            BPCIFQ.DATA.CORR_CCY = "156";
        }
        S000_CALL_BPZSIFQ();
        CEP.TRC(SCCGWA, BPCIFQ.RC.RTNCODE);
        if (BPCIFQ.RC.RTNCODE == 0) {
            IBS.init(SCCGWA, BPCMT);
            BPCMT.DATA.BR = BPB5081_AWA_5081.BR;
            BPCMT.DATA.EXR_TYPE = BPB5081_AWA_5081.EXR_TYP;
            BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5081_AWA_5081.CCY;
            BPCMT.DATA.CCY_INFO[1-1].CORR_CCY = BPCIFQ.DATA.CORR_CCY;
            BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPCIFQ.DATA.LOC_MID;
            WS_SP = BPB5081_AWA_5081.HFX_BP;
            BPB5081_AWA_5081.HFX_BP = BPB5081_AWA_5081.HCS_SP;
            BPB5081_AWA_5081.HCS_SP = WS_SP;
            if (BPB5081_AWA_5081.CMP_FLG == '0') {
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = BPCIFQ.DATA.LOC_MID * ( 1 - BPB5081_AWA_5081.HCS_BP ) / 100;
                bigD = new BigDecimal(BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = BPCIFQ.DATA.LOC_MID * ( 1 + BPB5081_AWA_5081.HCS_SP ) / 100;
                bigD = new BigDecimal(BPCMT.DATA.CCY_INFO[1-1].CCS_SELL);
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = BPCIFQ.DATA.LOC_MID * ( 1 - BPB5081_AWA_5081.HFX_BP ) / 100;
                bigD = new BigDecimal(BPCMT.DATA.CCY_INFO[1-1].CFX_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = BPCIFQ.DATA.LOC_MID * ( 1 + BPB5081_AWA_5081.HFX_SP ) / 100;
                bigD = new BigDecimal(BPCMT.DATA.CCY_INFO[1-1].CFX_SELL);
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (BPB5081_AWA_5081.CMP_FLG == '1') {
                IBS.init(SCCGWA, BPREXRD);
                IBS.init(SCCGWA, BPCTEXRM);
                BPREXRD.KEY.EXR_TYP = BPB5081_AWA_5081.EXR_TYP;
                BPREXRD.KEY.CCY = BPB5081_AWA_5081.CCY;
                BPCTEXRM.INFO.FUNC = 'Q';
                S000_CALL_BPZTEXRM();
                if (BPCTEXRM.RETURN_INFO == 'N') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE);
                }
                CEP.TRC(SCCGWA, BPCIFQ.DATA.LOC_MID);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_SELL);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_BUY);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_SELL);
                if (BPREXRD.EXR_PNT == '2') {
                    WS_DEC_MREM = BPCIFQ.DATA.LOC_MID % 1;
                    WS_DEC_MP2 = (BPCIFQ.DATA.LOC_MID - WS_DEC_MREM) / 1;
                    if (BPREXRD.EXR_RND == '1') {
                        WS_DEC_MP2 += .01;
                    }
                    if (BPREXRD.EXR_RND == '2') {
                        if (WS_DEC_MREM >= .005) {
                            WS_DEC_MP2 += .01;
                        }
                    }
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP2 - 0.01 * BPB5081_AWA_5081.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP2 + 0.01 * BPB5081_AWA_5081.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP2 - 0.01 * BPB5081_AWA_5081.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP2 + 0.01 * BPB5081_AWA_5081.HFX_SP;
                } else if (BPREXRD.EXR_PNT == '3') {
                    WS_DEC_MREM = BPCIFQ.DATA.LOC_MID % 1;
                    WS_DEC_MP3 = (BPCIFQ.DATA.LOC_MID - WS_DEC_MREM) / 1;
                    if (BPREXRD.EXR_RND == '1') {
                        WS_DEC_MP3 += .001;
                    }
                    if (BPREXRD.EXR_RND == '2') {
                        if (WS_DEC_MREM >= .0005) {
                            WS_DEC_MP3 += .001;
                        }
                    }
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP3 - 0.001 * BPB5081_AWA_5081.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP3 + 0.001 * BPB5081_AWA_5081.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP3 - 0.001 * BPB5081_AWA_5081.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP3 + 0.001 * BPB5081_AWA_5081.HFX_SP;
                } else if (BPREXRD.EXR_PNT == '4') {
                    WS_DEC_MREM = BPCIFQ.DATA.LOC_MID % 1;
                    WS_DEC_MP4 = (BPCIFQ.DATA.LOC_MID - WS_DEC_MREM) / 1;
                    if (BPREXRD.EXR_RND == '1') {
                        WS_DEC_MP4 += .0001;
                    }
                    if (BPREXRD.EXR_RND == '2') {
                        if (WS_DEC_MREM >= .00005) {
                            WS_DEC_MP4 += .0001;
                        }
                    }
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP4 - 0.0001 * BPB5081_AWA_5081.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP4 + 0.0001 * BPB5081_AWA_5081.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP4 - 0.0001 * BPB5081_AWA_5081.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP4 + 0.0001 * BPB5081_AWA_5081.HFX_SP;
                } else {
                }
            }
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_SELL);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_SELL);
            CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_BUY);
            CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_SELL);
            CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_BUY);
            CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_SELL);
            BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPCIFQ.DATA.CS_BUY;
            BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPCIFQ.DATA.CS_SELL;
            BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPCIFQ.DATA.FX_BUY;
            BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPCIFQ.DATA.FX_SELL;
            BPCMT.DATA.FUNC = 'A';
            S000_CALL_BPZMT();
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void S000_CALL_BPZDERT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-DEF-EXRT", BPCDERT);
        if (BPCDERT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCDERT.RC);
        }
    }
    public void S000_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MNT-TQP", BPCMT);
    }
    public void S000_CALL_BPZSIFQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQ-FX-QTP", BPCIFQ);
    }
    public void S000_CALL_BPZEXPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_EXPT, BPCEXPT);
        if (BPCEXPT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXPT.RC);
            WS_FLD_NO = BPB5081_AWA_5081.EXR_TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
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
