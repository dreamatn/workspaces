package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5164 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String CPN_INQ_BRW_CCY = "BP-R-BRW-CCY";
    int WS_DEC_MP0 = 0;
    double WS_DEC_MP1 = 0;
    double WS_DEC_MP2 = 0;
    double WS_DEC_MP3 = 0;
    double WS_DEC_MP4 = 0;
    double WS_DEC_MREM = 0;
    short WS_BROWSE_CNT = 0;
    short WS_I = 0;
    int WS_LEN = 0;
    int WS_LOC = 0;
    int WS_BR = 0;
    String WS_EXR_TYP = " ";
    String WS_VAL = " ";
    double WS_SP = 0;
    BPOT5164_WS_CCY_PNAM WS_CCY_PNAM = new BPOT5164_WS_CCY_PNAM();
    String WS_CCY_CODE = " ";
    String WS_CCY_CD = " ";
    int WS_EFF_DT = 0;
    short WS_CNT1 = 0;
    char WS_CCY_CD_FLG = ' ';
    double WS_EX_BID = 0;
    double WS_EX_OFFER = 0;
    double WS_CA_BID = 0;
    double WS_CA_OFFER = 0;
    String WS_EXR_TYP2 = " ";
    double WS_MID_RAT = 0;
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
    BPRBCCY BPRBCCY = new BPRBCCY();
    BPCRBCCY BPCRBCCY = new BPCRBCCY();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5164_AWA_5164 BPB5164_AWA_5164;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5164 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5164_AWA_5164>");
        BPB5164_AWA_5164 = (BPB5164_AWA_5164) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_BR = 999999;
        WS_LOC = 4;
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.CCY_PNAM);
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.EX_BID);
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.EX_OFFER);
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.CA_BID);
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.CA_OFFER);
        if (BPB5164_AWA_5164.CCY_PNAM.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PNAM_MUST_INPUT);
        } else {
            IBS.CPY2CLS(SCCGWA, BPB5164_AWA_5164.CCY_PNAM, WS_CCY_PNAM);
            if (WS_CCY_PNAM.WS_CCY_PNAM_1.equalsIgnoreCase(WS_CCY_PNAM.WS_CCY_PNAM_2)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_SAME_CCY);
            }
            if (WS_CCY_PNAM.WS_CCY_PNAM_2.equalsIgnoreCase("CNY") 
                && BPB5164_AWA_5164.SAFE_RAT == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_SAFE_RAT_MUST_INPUT);
            }
            if (WS_CCY_PNAM.WS_CCY_PNAM_2.equalsIgnoreCase("CNY")) {
                WS_EXR_TYP = "TRE";
                WS_EXR_TYP2 = "MDR";
                WS_CCY_CODE = WS_CCY_PNAM.WS_CCY_PNAM_1;
                WS_EX_BID = BPB5164_AWA_5164.EX_BID;
                WS_EX_OFFER = BPB5164_AWA_5164.EX_OFFER;
                WS_CA_BID = BPB5164_AWA_5164.CA_BID;
                WS_CA_OFFER = BPB5164_AWA_5164.CA_OFFER;
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PNAM_MUST_BE_CNY);
            }
            CEP.TRC(SCCGWA, WS_EXR_TYP);
            CEP.TRC(SCCGWA, WS_CCY_CODE);
            CEP.TRC(SCCGWA, "*** TRANSFER CCY ***");
            IBS.init(SCCGWA, BPRBCCY);
            IBS.init(SCCGWA, BPCRBCCY);
            BPRBCCY.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            BPRBCCY.KEY.CD = "000";
            WS_EFF_DT = 19000101;
            CEP.TRC(SCCGWA, "*** CALL BPZRBCCY***");
            T000_STARTBR_BPZRBCCY();
            T000_READNEXT_BPZRBCCY();
            for (WS_CNT1 = 1; WS_CNT1 <= 99 
                && WS_CCY_CD_FLG != 'S' 
                && BPCRBCCY.RETURN_INFO != 'N'; WS_CNT1 += 1) {
                CEP.TRC(SCCGWA, BPRBCCY.KEY.CD);
                CEP.TRC(SCCGWA, BPRBCCY.DESC);
                if (BPRBCCY.DESC.equalsIgnoreCase(WS_CCY_CODE)) {
                    WS_CCY_CD_FLG = 'S';
                    WS_CCY_CD = BPRBCCY.KEY.CD;
                } else {
                    T000_READNEXT_BPZRBCCY();
                }
            }
            T000_ENDBR_BPZRBCCY();
            if (BPCRBCCY.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND);
            }
            B010_INPUT_DATA();
        }
    }
    public void B010_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCREXPS);
        BPREXRP.KEY.EXR_TYP = WS_EXR_TYP;
        BPREXRP.KEY.CCY = WS_CCY_CD;
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = WS_EXR_TYP;
        BPREXRD.KEY.CCY = WS_CCY_CD;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (BPCTEXRM.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE);
        }
        CEP.TRC(SCCGWA, BPREXRD.UNT);
        CEP.TRC(SCCGWA, BPB5164_AWA_5164.UNIT);
        if (BPREXRD.UNT != BPB5164_AWA_5164.UNIT) {
            CEP.TRC(SCCGWA, "DIFF-UNT");
            WS_EX_BID = WS_EX_BID / BPB5164_AWA_5164.UNIT * BPREXRD.UNT;
            WS_EX_OFFER = WS_EX_OFFER / BPB5164_AWA_5164.UNIT * BPREXRD.UNT;
            WS_CA_BID = WS_CA_BID / BPB5164_AWA_5164.UNIT * BPREXRD.UNT;
            WS_CA_OFFER = WS_CA_OFFER / BPB5164_AWA_5164.UNIT * BPREXRD.UNT;
            CEP.TRC(SCCGWA, BPREXRD.UNT);
            CEP.TRC(SCCGWA, BPB5164_AWA_5164.UNIT);
            if (BPREXRD.UNT != BPB5164_AWA_5164.UNIT) {
                CEP.TRC(SCCGWA, "DIFF-UNT");
                WS_MID_RAT = BPB5164_AWA_5164.SAFE_RAT / BPB5164_AWA_5164.UNIT * BPREXRD.UNT;
            } else {
                WS_MID_RAT = BPB5164_AWA_5164.SAFE_RAT;
            }
            CEP.TRC(SCCGWA, WS_MID_RAT);
        }
        if (WS_EXR_TYP.equalsIgnoreCase("TRE")) {
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
            if (WS_EXR_TYP2.equalsIgnoreCase("MDR")) {
                WS_EXR_TYP = "MDR";
                B020_ADD_PROC_2();
            }
        } else {
            B020_ADD_PROC_2();
        }
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
        BPCMT.DATA.EXR_TYPE = WS_EXR_TYP;
        BPCMT.DATA.CCY_INFO[1-1].CCY = WS_CCY_CD;
        B005_INQ_PROC();
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = WS_MID_RAT;
        if (WS_EXR_TYP.equalsIgnoreCase("MDR")) {
            BPCMT.DATA.CCY_INFO[1-1].MID_RAT = WS_MID_RAT;
        }
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = WS_EX_BID;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = WS_EX_OFFER;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = WS_CA_BID;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = WS_CA_OFFER;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        WS_SP = BPREXRP.HFX_BP;
        BPREXRP.HFX_BP = BPREXRP.HCS_SP;
        BPREXRP.HCS_SP = WS_SP;
        CEP.TRC(SCCGWA, BPREXRP.HFX_BP);
        CEP.TRC(SCCGWA, BPREXRP.HCS_SP);
        if (BPREXRP.CMP_FLG == '0') {
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_BUY = BPCMT.DATA.CCY_INFO[1-1].MID_RAT * ( 1 - BPREXRP.HCS_BP / 100 );
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CCS_BUY);
            BPCMT.DATA.CCY_INFO[1-1].CCS_SELL = BPCMT.DATA.CCY_INFO[1-1].MID_RAT * ( 1 + BPREXRP.HCS_SP / 100 );
            BPCMT.DATA.CCY_INFO[1-1].CFX_BUY = BPCMT.DATA.CCY_INFO[1-1].MID_RAT * ( 1 - BPREXRP.HFX_BP / 100 );
            BPCMT.DATA.CCY_INFO[1-1].CFX_SELL = BPCMT.DATA.CCY_INFO[1-1].MID_RAT * ( 1 + BPREXRP.HFX_SP / 100 );
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
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].MID_RAT);
            if (BPREXRD.EXR_PNT == '2') {
                WS_DEC_MREM = BPCMT.DATA.CCY_INFO[1-1].MID_RAT % 1;
                WS_DEC_MP2 = (BPCMT.DATA.CCY_INFO[1-1].MID_RAT - WS_DEC_MREM) / 1;
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
                WS_DEC_MREM = BPCMT.DATA.CCY_INFO[1-1].MID_RAT % 1;
                WS_DEC_MP3 = (BPCMT.DATA.CCY_INFO[1-1].MID_RAT - WS_DEC_MREM) / 1;
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
                WS_DEC_MREM = BPCMT.DATA.CCY_INFO[1-1].MID_RAT % 1;
                WS_DEC_MP4 = (BPCMT.DATA.CCY_INFO[1-1].MID_RAT - WS_DEC_MREM) / 1;
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
            CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].MID_RAT);
        }
        BPCMT.DATA.FUNC = 'A';
        S00_CALL_BPZMT();
    }
    public void B005_INQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_EXR_TYP);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = WS_EXR_TYP;
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
        BPCMT.DATA.BR = WS_BR;
        BPCMT.DATA.EXR_TYPE = WS_EXR_TYP;
        BPCMT.DATA.CCY_INFO[1-1].CCY = WS_CCY_CD;
        B005_INQ_PROC();
        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = WS_MID_RAT;
        if (WS_EXR_TYP.equalsIgnoreCase("MDR")) {
            BPCMT.DATA.CCY_INFO[1-1].MID_RAT = WS_MID_RAT;
        }
        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = WS_EX_BID;
        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = WS_EX_OFFER;
        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = WS_CA_BID;
        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = WS_CA_OFFER;
        BPCMT.DATA.FUNC = 'A';
        S00_CALL_BPZMT();
    }
    public void S000_CALL_BPZTEXPS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVHZ1922");
        BPCREXPS.INFO.POINTER = BPREXRP;
        BPCREXPS.INFO.LEN = 235;
        CEP.TRC(SCCGWA, "DEVHZ2222");
        IBS.CALLCPN(SCCGWA, "BP-R-EXRP-B", BPCREXPS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
    }
    public void T000_STARTBR_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'S';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
    }
    public void T000_READNEXT_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'R';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
    }
    public void T000_ENDBR_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'E';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
    }
    public void S000_CALL_BPZRBCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_BRW_CCY, BPCRBCCY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
