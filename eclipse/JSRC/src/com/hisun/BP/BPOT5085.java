package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5085 {
    String CPN_S_EXPT = "BP-DEF-EXPT      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_MREM = 0;
    int WS_LEN = 0;
    int WS_LOC = 0;
    double WS_SP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCEXPT BPCEXPT = new BPCEXPT();
    BPCIFQ BPCIFQ = new BPCIFQ();
    BPCMT BPCMT = new BPCMT();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCEXRP BPCEXRP = new BPCEXRP();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5085_AWA_5085 BPB5085_AWA_5085;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5085 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5085_AWA_5085>");
        BPB5085_AWA_5085 = (BPB5085_AWA_5085) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXRP);
        BPCEXRP.BR = BPB5085_AWA_5085.BR;
        BPCEXRP.EXR_TYP = BPB5085_AWA_5085.EXR_TYP;
        BPCEXRP.CCY = BPB5085_AWA_5085.CCY;
        BPCEXRP.CMP_FLG = BPB5085_AWA_5085.FXSC_MTH;
        BPCEXRP.HCS_BP = BPB5085_AWA_5085.HCS_BP;
        BPCEXRP.HFX_BP = BPB5085_AWA_5085.HFX_BP;
        BPCEXRP.HCS_SP = BPB5085_AWA_5085.HCS_SP;
        BPCEXRP.HFX_SP = BPB5085_AWA_5085.HFX_SP;
        B020_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPCEXRP.EXR_TYP;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE);
        }
        WS_CCY = BPCEXRP.CCY;
        R000_CHECK_CCY();
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        if (BPREXRT.DAT_TXT.BASE_CCY.equalsIgnoreCase(BPCEXRP.CCY)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_NOT_DEF_EX_CCY);
        }
        CEP.TRC(SCCGWA, BPCEXRP.CMP_FLG);
        if (BPCEXRP.CMP_FLG != '0' 
            && BPCEXRP.CMP_FLG != '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_COM_FLG_ERR);
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCQCCY.RC);
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXPT);
        BPCEXPT.FUNC = 'A';
        BPCEXPT.BR = BPCEXRP.BR;
        BPCEXPT.EXR_TYP = BPCEXRP.EXR_TYP;
        BPCEXPT.CCY = BPCEXRP.CCY;
        BPCEXPT.CMP_FLG = BPCEXRP.CMP_FLG;
        BPCEXPT.HCS_BP = BPCEXRP.HCS_BP;
        BPCEXPT.HFX_BP = BPCEXRP.HFX_BP;
        BPCEXPT.HCS_SP = BPCEXRP.HCS_SP;
        BPCEXPT.HFX_SP = BPCEXRP.HFX_SP;
        BPCEXPT.CS_BP = BPCEXRP.CS_BP;
        BPCEXPT.FX_BP = BPCEXRP.FX_BP;
        BPCEXPT.CS_SP = BPCEXRP.CS_SP;
        BPCEXPT.FX_SP = BPCEXRP.FX_SP;
        S000_CALL_BPZEXPT();
        B030_UPD_BPTTQP();
    }
    public void B030_UPD_BPTTQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFQ);
        BPCIFQ.DATA.EXR_TYPE = BPCEXRP.EXR_TYP;
        BPCIFQ.DATA.BR = 706660800;
        BPCIFQ.DATA.CCY = BPCEXRP.CCY;
        B005_INQ_PROC();
        S000_CALL_BPZSIFQ();
        CEP.TRC(SCCGWA, BPCIFQ.RC.RTNCODE);
        if (BPCIFQ.RC.RTNCODE == 0) {
            IBS.init(SCCGWA, BPCMT);
            BPCMT.DATA.BR = BPCEXRP.BR;
            BPCMT.DATA.EXR_TYPE = BPCEXRP.EXR_TYP;
            BPCMT.DATA.CCY_INFO[1-1].CCY = BPCEXRP.CCY;
            BPCMT.DATA.CCY_INFO[1-1].CORR_CCY = BPCIFQ.DATA.CORR_CCY;
            BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPCIFQ.DATA.LOC_MID;
            CEP.TRC(SCCGWA, BPCEXRP.HFX_BP);
            CEP.TRC(SCCGWA, BPCEXRP.HCS_SP);
            WS_SP = BPCEXRP.HFX_BP;
            BPCEXRP.HFX_BP = BPCEXRP.HCS_SP;
            BPCEXRP.HCS_SP = WS_SP;
            CEP.TRC(SCCGWA, BPCEXRP.HFX_BP);
            CEP.TRC(SCCGWA, BPCEXRP.HCS_SP);
            if (BPCEXRP.CMP_FLG == '0') {
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = BPCIFQ.DATA.LOC_MID * ( 1 - BPCEXRP.HCS_BP / 100 );
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = BPCIFQ.DATA.LOC_MID * ( 1 + BPCEXRP.HCS_SP / 100 );
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = BPCIFQ.DATA.LOC_MID * ( 1 - BPCEXRP.HFX_BP / 100 );
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = BPCIFQ.DATA.LOC_MID * ( 1 + BPCEXRP.HFX_SP / 100 );
            }
            if (BPCEXRP.CMP_FLG == '1') {
                IBS.init(SCCGWA, BPREXRD);
                IBS.init(SCCGWA, BPCTEXRM);
                BPREXRD.KEY.EXR_TYP = BPCEXRP.EXR_TYP;
                BPREXRD.KEY.CCY = BPCEXRP.CCY;
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
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP2 - 0.01 * BPCEXRP.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP2 + 0.01 * BPCEXRP.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP2 - 0.01 * BPCEXRP.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP2 + 0.01 * BPCEXRP.HFX_SP;
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
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP3 - 0.001 * BPCEXRP.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP3 + 0.001 * BPCEXRP.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP3 - 0.001 * BPCEXRP.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP3 + 0.001 * BPCEXRP.HFX_SP;
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
                    BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP4 - 0.0001 * BPCEXRP.HCS_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP4 + 0.0001 * BPCEXRP.HCS_SP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP4 - 0.0001 * BPCEXRP.HFX_BP;
                    BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP4 + 0.0001 * BPCEXRP.HFX_SP;
                } else {
                }
            }
            BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPCIFQ.DATA.CS_BUY;
            BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPCIFQ.DATA.CS_SELL;
            BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPCIFQ.DATA.FX_BUY;
            BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPCIFQ.DATA.FX_SELL;
            BPCMT.DATA.FUNC = 'A';
            S000_CALL_BPZMT();
        }
    }
    public void B005_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXRP.EXR_TYP);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPCEXRP.EXR_TYP;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
        BPCIFQ.DATA.CORR_CCY = BPREXRT.DAT_TXT.BASE_CCY;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void S000_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MNT-TQP", BPCMT);
    }
    public void S000_CALL_BPZSIFQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQ-FX-QTP", BPCIFQ);
    }
    public void S000_CALL_BPZEXPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_EXPT, BPCEXPT, true);
        if (BPCEXPT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCEXPT.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
