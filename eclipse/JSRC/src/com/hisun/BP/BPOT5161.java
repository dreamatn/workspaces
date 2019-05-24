package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5161 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    int K_MAX_CNT = 20;
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_T = 0;
    short WS_L = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_DEC_MP0 = 0;
    double WS_DEC_MP1 = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_YD2 = 0;
    double WS_DEC_YD3 = 0;
    double WS_DEC_YD4 = 0;
    double WS_DEC_MREM = 0;
    double WS_DEC_YDEM = 0;
    double WS_SP = 0;
    BPOT5161_WS_OUT_DATA WS_OUT_DATA = new BPOT5161_WS_OUT_DATA();
    short WS_BROWSE_CNT = 0;
    char WS_CCY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCMT BPCMT = new BPCMT();
    BPREXRP BPREXRP = new BPREXRP();
    BPCREXPS BPCREXPS = new BPCREXPS();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5160_AWA_5160 BPB5160_AWA_5160;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5161 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5160_AWA_5160>");
        BPB5160_AWA_5160 = (BPB5160_AWA_5160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CEP.TRC(SCCGWA, "BRW-BPTEXRP");
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCREXPS);
        BPREXRP.KEY.EXR_TYP = BPB5160_AWA_5160.EXR_TYPE;
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            if (BPB5160_AWA_5160.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                BPREXRP.KEY.CCY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CCY;
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
                CEP.TRC(SCCGWA, "DEVHZ");
                CEP.TRC(SCCGWA, WS_BROWSE_CNT);
            }
        }
        CEP.TRC(SCCGWA, "READ-END");
        BPCREXPS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXPS();
        CEP.TRC(SCCGWA, "DEVHZ08042");
        B030_BRW_PROC();
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
        BPCMT.DATA.EXR_TYPE = BPB5160_AWA_5160.EXR_TYPE;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CCY;
        if (BPB5160_AWA_5160.EXR_TYPE.equalsIgnoreCase("TRE") 
            || BPB5160_AWA_5160.EXR_TYPE.equalsIgnoreCase("MDR")) {
            BPCMT.DATA.CCY_INFO[1-1].CORR_CCY = "156";
        }
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID;
        BPCMT.DATA.CCY_INFO[1-1].RECENT_MID_RAT = BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID;
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].FX_BUY;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].FX_SELL;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CS_BUY;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CS_SELL;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        WS_SP = BPREXRP.HFX_BP;
        BPREXRP.HFX_BP = BPREXRP.HCS_SP;
        BPREXRP.HCS_SP = WS_SP;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        if (BPREXRP.CMP_FLG == '0') {
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID * ( 1 - BPREXRP.HCS_BP );
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID * ( 1 + BPREXRP.HCS_SP );
            BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID * ( 1 - BPREXRP.HFX_BP );
            BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID * ( 1 + BPREXRP.HFX_SP );
        }
        CEP.TRC(SCCGWA, BPREXRP.CMP_FLG);
        if (BPREXRP.CMP_FLG == '1') {
            IBS.init(SCCGWA, BPREXRD);
            IBS.init(SCCGWA, BPCTEXRM);
            BPREXRD.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
            BPREXRD.KEY.CCY = BPCMT.DATA.CCY_INFO[1-1].CCY;
            BPCTEXRM.INFO.FUNC = 'Q';
            S000_CALL_BPZTEXRM();
            CEP.TRC(SCCGWA, BPCTEXRM.RC.RC_CODE);
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
                WS_DEC_MREM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID % 1;
                WS_DEC_MP2 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID - WS_DEC_MREM) / 1;
                WS_DEC_YDEM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID % 1;
                WS_DEC_YD2 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID - WS_DEC_YDEM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP2 += .01;
                    WS_DEC_YD2 += .01;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .005) {
                        WS_DEC_MP2 += .01;
                    }
                    if (WS_DEC_YDEM >= .005) {
                        WS_DEC_YD2 += .01;
                    }
                }
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP2 - 0.01 * BPREXRP.HCS_BP;
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP2 + 0.01 * BPREXRP.HCS_SP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP2 - 0.01 * BPREXRP.HFX_BP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP2 + 0.01 * BPREXRP.HFX_SP;
            } else if (BPREXRD.EXR_PNT == '3') {
                WS_DEC_MREM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID % 1;
                WS_DEC_MP3 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID - WS_DEC_MREM) / 1;
                WS_DEC_YDEM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID % 1;
                WS_DEC_YD3 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID - WS_DEC_YDEM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP3 += .001;
                    WS_DEC_YD3 += .001;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .0005) {
                        WS_DEC_MP3 += .001;
                    }
                    if (WS_DEC_YDEM >= .0005) {
                        WS_DEC_YD3 += .001;
                    }
                }
                BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = WS_DEC_MP3 - 0.001 * BPREXRP.HCS_BP;
                BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = WS_DEC_MP3 + 0.001 * BPREXRP.HCS_SP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = WS_DEC_MP3 - 0.001 * BPREXRP.HFX_BP;
                BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = WS_DEC_MP3 + 0.001 * BPREXRP.HFX_SP;
            } else if (BPREXRD.EXR_PNT == '4') {
                WS_DEC_MREM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID % 1;
                WS_DEC_MP4 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID - WS_DEC_MREM) / 1;
                WS_DEC_YDEM = BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID % 1;
                WS_DEC_YD4 = (BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID - WS_DEC_YDEM) / 1;
                if (BPREXRD.EXR_RND == '1') {
                    WS_DEC_MP4 += .0001;
                    WS_DEC_YD4 += .0001;
                }
                if (BPREXRD.EXR_RND == '2') {
                    if (WS_DEC_MREM >= .00005) {
                        WS_DEC_MP4 += .0001;
                    }
                    if (WS_DEC_YDEM >= .00005) {
                        WS_DEC_YD4 += .0001;
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
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void B020_ADD_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.BR = BPB5160_AWA_5160.BR;
        BPCMT.DATA.EXR_TYPE = BPB5160_AWA_5160.EXR_TYPE;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CCY;
        if (BPB5160_AWA_5160.EXR_TYPE.equalsIgnoreCase("TRE") 
            || BPB5160_AWA_5160.EXR_TYPE.equalsIgnoreCase("MDR")) {
            BPCMT.DATA.CCY_INFO[1-1].CORR_CCY = "156";
        }
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPB5160_AWA_5160.CCY_INFO[WS_I-1].TD_MID;
        BPCMT.DATA.CCY_INFO[1-1].RECENT_MID_RAT = BPB5160_AWA_5160.CCY_INFO[WS_I-1].YD_MID;
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].FX_BUY;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].FX_SELL;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CS_BUY;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPB5160_AWA_5160.CCY_INFO[WS_I-1].CS_SELL;
        BPCMT.DATA.FUNC = 'A';
        S00_CALL_BPZMT();
    }
    public void S000_CALL_BPZTEXPS() throws IOException,SQLException,Exception {
        BPCREXPS.INFO.POINTER = BPREXRP;
        BPCREXPS.INFO.LEN = 235;
        CEP.TRC(SCCGWA, "DEVHZ2222");
        IBS.CALLCPN(SCCGWA, "BP-R-EXRP-B", BPCREXPS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.EXR_TYPE);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.BR);
        if (BPB5160_AWA_5160.BR != 706660800) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.BR_ERROR);
        }
        WS_K = 0;
        WS_T = 0;
        WS_L = 0;
        for (WS_K = 1; WS_K <= 20 
            && BPB5160_AWA_5160.CCY_INFO[WS_K-1].CCY.trim().length() != 0; WS_K += 1) {
            WS_L = (short) (WS_K + 1);
            for (WS_T = WS_L; WS_T <= 20 
                && BPB5160_AWA_5160.CCY_INFO[WS_T-1].CCY.trim().length() != 0; WS_T += 1) {
                CEP.TRC(SCCGWA, WS_T);
                CEP.TRC(SCCGWA, WS_K);
                CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[WS_K-1].CCY);
                CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[WS_T-1].CCY);
                if (BPB5160_AWA_5160.CCY_INFO[WS_K-1].CCY.equalsIgnoreCase(BPB5160_AWA_5160.CCY_INFO[WS_T-1].CCY)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REPEATED);
                }
            }
        }
    }
    public void B030_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.FUNC = 'B';
        BPCMT.DATA.EXR_TYPE = BPB5160_AWA_5160.EXR_TYPE;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5160_AWA_5160.CCY_INFO[1-1].CCY;
        BPCMT.DATA.CONT_FLAG = 'Y';
        BPCMT.DATA.CMPL_CNT = BPB5160_AWA_5160.CMPL_CNT;
        S00_CALL_BPZMT();
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
