package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5257 {
    String K_OUTPUT_FMT = "BP720";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5257_WS_RATE_OUTPUT WS_RATE_OUTPUT = new BPOT5257_WS_RATE_OUTPUT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMMRT BPCSMMRT = new BPCSMMRT();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    SCCGWA SCCGWA;
    BPB5250_AWA_5250 BPB5250_AWA_5250;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5257 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5250_AWA_5250>");
        BPB5250_AWA_5250 = (BPB5250_AWA_5250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.CCY);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.RATE_TYP);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.BRT_TBL[1-1].TENOR);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.FUNC);
        if (BPB5250_AWA_5250.FUNC == 'O' 
            || BPB5250_AWA_5250.FUNC == 'Q') {
            IBS.init(SCCGWA, BPCOQRTD);
            if (BPB5250_AWA_5250.BRT_TBL[1-1].RATEID.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB5250_AWA_5250.BRT_TBL[1-1].RATEID);
                BPCOQRTD.DATA.RATE_ID = BPB5250_AWA_5250.BRT_TBL[1-1].RATEID;
                BPCOQRTD.INQ_FLG = 'R';
                S000_CALL_BPZPQRTD();
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.CCY);
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.BASE_TYP);
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.TENOR);
                if (BPCOQRTD.DATA.CCY.trim().length() == 0 
                    && BPB5250_AWA_5250.FUNC == 'O') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
                if (!BPCOQRTD.DATA.TENOR.equalsIgnoreCase("999") 
                    && BPB5250_AWA_5250.FUNC == 'Q') {
                    BPCOQRTD.DATA.CCY = " ";
                    BPCOQRTD.DATA.BASE_TYP = " ";
                }
            } else {
                CEP.TRC(SCCGWA, BPB5250_AWA_5250.CCY);
                CEP.TRC(SCCGWA, BPB5250_AWA_5250.RATE_TYP);
                CEP.TRC(SCCGWA, BPB5250_AWA_5250.BRT_TBL[1-1].TENOR);
                BPCOQRTD.DATA.CCY = BPB5250_AWA_5250.CCY;
                BPCOQRTD.DATA.BASE_TYP = BPB5250_AWA_5250.RATE_TYP;
                BPCOQRTD.DATA.TENOR = BPB5250_AWA_5250.BRT_TBL[1-1].TENOR;
                BPCOQRTD.INQ_FLG = 'C';
                S000_CALL_BPZPQRTD();
                CEP.TRC(SCCGWA, BPCOQRTD.DATA.RATE_ID);
                if (BPCOQRTD.DATA.RATE_ID.trim().length() == 0 
                    && BPB5250_AWA_5250.FUNC == 'O') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
            }
            B000_01_OUTPUT_PROC();
        } else {
            B020_BRATE_INFO_QUERY();
            B030_SET_SUB_TRN();
        }
    }
    public void B020_BRATE_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMMRT);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.FUNC);
        if (BPB5250_AWA_5250.FUNC == 'B' 
            || BPB5250_AWA_5250.FUNC == 'E') {
            BPCSMMRT.FUNC = 'N';
            if (BPB5250_AWA_5250.FUNC == 'B') {
                BPCSMMRT.BRW_BTH_OPT = 'M';
            } else {
                BPCSMMRT.BRW_BTH_OPT = 'B';
            }
            BPCSMMRT.BASE_TYP = BPB5250_AWA_5250.RATE_TYP;
            BPCSMMRT.CCY = BPB5250_AWA_5250.CCY;
            BPCSMMRT.UPD_DATA[1-1].TENOR = BPB5250_AWA_5250.BRT_TBL[1-1].TENOR;
        } else {
            BPCSMMRT.FUNC = 'I';
            BPCSMMRT.BASE_TYP = BPB5250_AWA_5250.RATE_TYP;
            BPCSMMRT.CCY = BPB5250_AWA_5250.CCY;
            BPCSMMRT.UPD_DATA[1-1].TENOR = BPB5250_AWA_5250.BRT_TBL[1-1].TENOR;
            BPCSMMRT.UPD_DATA[1-1].OEFF_DT = BPB5250_AWA_5250.BRT_TBL[1-1].OEFF_DT;
        }
        S010_CALL_BPZSMMRT();
    }
    public void S010_CALL_BPZSMMRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MARKET-RATE", BPCSMMRT);
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB5250_AWA_5250.FUNC == 'I') {
            SCCSUBS.TR_CODE = 5252;
        } else if (BPB5250_AWA_5250.FUNC == 'M') {
            SCCSUBS.TR_CODE = 5253;
        } else if (BPB5250_AWA_5250.FUNC == 'D') {
            SCCSUBS.TR_CODE = 5254;
        } else if (BPB5250_AWA_5250.FUNC == 'B') {
            SCCSUBS.TR_CODE = 5256;
        } else if (BPB5250_AWA_5250.FUNC == 'E') {
            SCCSUBS.TR_CODE = 5246;
        } else {
            SCCSUBS.TR_CODE = 5252;
        }
        S000_SET_SUBS_TRN();
    }
    public void B000_01_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_RATE_OUTPUT);
        WS_RATE_OUTPUT.WS_RATE_TYPE = BPCOQRTD.DATA.BASE_TYP;
        WS_RATE_OUTPUT.WS_CCY = BPCOQRTD.DATA.CCY;
        WS_RATE_OUTPUT.WS_TENOR = BPCOQRTD.DATA.TENOR;
        WS_RATE_OUTPUT.WS_RATE_ID = BPCOQRTD.DATA.RATE_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_RATE_OUTPUT;
        SCCFMT.DATA_LEN = 20;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
