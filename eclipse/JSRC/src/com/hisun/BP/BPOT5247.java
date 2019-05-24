package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5247 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMBRT BPCSMBRT = new BPCSMBRT();
    SCCGWA SCCGWA;
    BPB5240_AWA_5240 BPB5240_AWA_5240;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5247 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5240_AWA_5240>");
        BPB5240_AWA_5240 = (BPB5240_AWA_5240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_BRATE_INFO_QUERY();
        B030_SET_SUB_TRN();
    }
    public void B020_BRATE_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMBRT);
        if (BPB5240_AWA_5240.FUNC == 'B') {
            BPCSMBRT.FUNC = 'N';
            BPCSMBRT.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
            BPCSMBRT.UPD_DATA[1-1].CCY = BPB5240_AWA_5240.BRT_TBL[1-1].CCY;
        } else {
            BPCSMBRT.FUNC = 'I';
            BPCSMBRT.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
            BPCSMBRT.UPD_DATA[1-1].CCY = BPB5240_AWA_5240.BRT_TBL[1-1].CCY;
            BPCSMBRT.UPD_DATA[1-1].OEFF_DT = BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT;
        }
        S010_CALL_BPZSMBRT();
    }
    public void S010_CALL_BPZSMBRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BASE-RATE", BPCSMBRT);
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB5240_AWA_5240.FUNC == 'I') {
            SCCSUBS.TR_CODE = 5242;
        } else if (BPB5240_AWA_5240.FUNC == 'M') {
            SCCSUBS.TR_CODE = 5243;
        } else if (BPB5240_AWA_5240.FUNC == 'D') {
            SCCSUBS.TR_CODE = 5244;
        } else if (BPB5240_AWA_5240.FUNC == 'B') {
            CEP.TRC(SCCGWA, " BEGIN 9995246");
            SCCSUBS.TR_CODE = 5246;
        } else {
            SCCSUBS.TR_CODE = 5242;
        }
        S000_SET_SUBS_TRN();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
