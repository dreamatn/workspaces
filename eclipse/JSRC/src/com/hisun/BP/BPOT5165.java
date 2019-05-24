package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5165 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    int WS_DEC_MP0 = 0;
    double WS_DEC_MP1 = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_MREM = 0;
    short WS_BROWSE_CNT = 0;
    int WS_LEN = 0;
    int WS_LOC = 0;
    double WS_SP = 0;
    char WS_CCY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARM BPRPARM = new BPRPARM();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCMT BPCMT = new BPCMT();
    BPREXRP BPREXRP = new BPREXRP();
    BPCREXPS BPCREXPS = new BPCREXPS();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    String LK_REC = " ";
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5165_AWA_5165 BPB5165_AWA_5165;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5165 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5165_AWA_5165>");
        BPB5165_AWA_5165 = (BPB5165_AWA_5165) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.EXR_TYP);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.CCY);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.TD_MID);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.CS_BUY);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.CS_SELL);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.FX_BUY);
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.FX_SELL);
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCREXPS);
        BPREXRP.KEY.EXR_TYP = BPB5165_AWA_5165.EXR_TYP;
        BPREXRP.KEY.CCY = BPB5165_AWA_5165.CCY;
        BPCREXPS.INFO.FUNC = '1';
        S000_CALL_BPZTEXPS();
        BPCREXPS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXPS();
        if (BPCREXPS.INFO.RTN_INFO == 'Y') {
            B020_READ_NEXT();
            B020_ADD_PROC_2();
        } else if (BPCREXPS.INFO.RTN_INFO == 'N') {
            B020_ADD_PROC_2();
        } else {
        }
        CEP.TRC(SCCGWA, WS_BROWSE_CNT);
        CEP.TRC(SCCGWA, "READ-END");
        BPCREXPS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXPS();
    }
    public void B020_READ_NEXT() throws IOException,SQLException,Exception {
        while (BPCREXPS.INFO.RTN_INFO != 'N') {
            WS_BROWSE_CNT += 1;
            B020_ADD_PROC_1();
            BPCREXPS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXPS();
        }
    }
    public void B020_ADD_PROC_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.BR = BPREXRP.KEY.BR;
        BPCMT.DATA.EXR_TYPE = BPB5165_AWA_5165.EXR_TYP;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5165_AWA_5165.CCY;
        B005_INQ_PROC();
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPB5165_AWA_5165.TD_MID;
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPB5165_AWA_5165.FX_BUY;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPB5165_AWA_5165.FX_SELL;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPB5165_AWA_5165.CS_BUY;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPB5165_AWA_5165.CS_SELL;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        WS_SP = BPREXRP.HFX_BP;
        BPREXRP.HFX_BP = BPREXRP.HCS_SP;
        BPREXRP.HCS_SP = WS_SP;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        if (BPREXRP.CMP_FLG == '0') {
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = BPB5165_AWA_5165.TD_MID * ( 1 - BPREXRP.HCS_BP / 100 );
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = BPB5165_AWA_5165.TD_MID * ( 1 + BPREXRP.HCS_SP / 100 );
            BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = BPB5165_AWA_5165.TD_MID * ( 1 - BPREXRP.HFX_BP / 100 );
            BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = BPB5165_AWA_5165.TD_MID * ( 1 + BPREXRP.HFX_SP / 100 );
        }
        if (BPREXRP.CMP_FLG == '1') {
            IBS.init(SCCGWA, BPREXRD);
            IBS.init(SCCGWA, BPCTEXRM);
            BPREXRD.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
            BPREXRD.KEY.CCY = BPCMT.DATA.CCY_INFO[1-1].CCY;
            BPCTEXRM.INFO.FUNC = 'Q';
            S000_CALL_BPZTEXRM();
            if (BPCTEXRM.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE);
            }
            CEP.TRC(SCCGWA, BPREXRD.EXR_PNT);
            CEP.TRC(SCCGWA, BPREXRD.EXR_RND);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_SELL);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_SELL);
            if (BPREXRD.EXR_PNT == '2') {
                WS_DEC_MREM = BPB5165_AWA_5165.TD_MID % 1;
                WS_DEC_MP2 = (BPB5165_AWA_5165.TD_MID - WS_DEC_MREM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP2 += .01;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .005) {
                        WS_DEC_MP2 += .01;
                    }
                }
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP2 - 0.01 * BPREXRP.HCS_BP;
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP2 + 0.01 * BPREXRP.HCS_SP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP2 - 0.01 * BPREXRP.HFX_BP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP2 + 0.01 * BPREXRP.HFX_SP;
            } else if (BPREXRD.EXR_PNT == '3') {
                WS_DEC_MREM = BPB5165_AWA_5165.TD_MID % 1;
                WS_DEC_MP3 = (BPB5165_AWA_5165.TD_MID - WS_DEC_MREM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP3 += .001;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .0005) {
                        WS_DEC_MP3 += .001;
                    }
                }
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP3 - 0.001 * BPREXRP.HCS_BP;
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP3 + 0.001 * BPREXRP.HCS_SP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP3 - 0.001 * BPREXRP.HFX_BP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP3 + 0.001 * BPREXRP.HFX_SP;
            } else if (BPREXRD.EXR_PNT == '4') {
                WS_DEC_MREM = BPB5165_AWA_5165.TD_MID % 1;
                WS_DEC_MP4 = (BPB5165_AWA_5165.TD_MID - WS_DEC_MREM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP4 += .0001;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .00005) {
                        WS_DEC_MP4 += .0001;
                    }
                }
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP4 - 0.0001 * BPREXRP.HCS_BP;
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP4 + 0.0001 * BPREXRP.HCS_SP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP4 - 0.0001 * BPREXRP.HFX_BP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP4 + 0.0001 * BPREXRP.HFX_SP;
            } else {
            }
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_SELL);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_BUY);
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CFX_SELL);
        }
        BPCMT.DATA.FUNC = 'A';
        S00_CALL_BPZMT();
    }
    public void B005_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5165_AWA_5165.EXR_TYP);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5165_AWA_5165.EXR_TYP;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
        BPCMT.DATA.CCY_INFO[1-1].CORR_CCY = BPREXRT.DAT_TXT.BASE_CCY;
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CORR_CCY);
        CEP.TRC(SCCGWA, "DEVHZ450");
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void B020_ADD_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.BR = 999999;
        BPCMT.DATA.EXR_TYPE = BPB5165_AWA_5165.EXR_TYP;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5165_AWA_5165.CCY;
        B005_INQ_PROC();
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPB5165_AWA_5165.TD_MID;
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPB5165_AWA_5165.FX_BUY;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPB5165_AWA_5165.FX_SELL;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPB5165_AWA_5165.CS_BUY;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPB5165_AWA_5165.CS_SELL;
        BPCMT.DATA.FUNC = 'A';
        S00_CALL_BPZMT();
    }
    public void S000_CALL_BPZTEXPS() throws IOException,SQLException,Exception {
        BPCREXPS.INFO.POINTER = BPREXRP;
        BPCREXPS.INFO.LEN = 235;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRP-B", BPCREXPS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
