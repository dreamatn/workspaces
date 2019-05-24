package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5245 {
    int JIBS_tmp_int;
    String K_RATEID = "RRTID";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTID BPRRTID = new BPRRTID();
    BPCSMBRT BPCSMBRT = new BPCSMBRT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5240_AWA_5240 BPB5240_AWA_5240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5245 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5240_AWA_5240>");
        BPB5240_AWA_5240 = (BPB5240_AWA_5240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[1-1]);
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.RATE_TYP);
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[1-1].CCY);
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT);
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[1-1].RATEID);
        if (BPB5240_AWA_5240.BRT_TBL[1-1].ORATE > 0) {
            CEP.TRC(SCCGWA, "ORATE > ZERO");
        }
        IBS.init(SCCGWA, BPCSMBRT);
        BPCSMBRT.FUNC = 'A';
        BPCSMBRT.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
        BPCSMBRT.UPD_DATA[1-1].CCY = BPB5240_AWA_5240.BRT_TBL[1-1].CCY;
        BPCSMBRT.UPD_DATA[1-1].OEFF_DT = BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT;
        BPCSMBRT.UPD_DATA[1-1].ORATE = BPB5240_AWA_5240.BRT_TBL[1-1].ORATE;
        BPCSMBRT.UPD_DATA[1-1].RATE_ID = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID;
        CEP.TRC(SCCGWA, BPCSMBRT.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSMBRT.UPD_DATA[1-1].CCY);
        CEP.TRC(SCCGWA, BPCSMBRT.UPD_DATA[1-1].OEFF_DT);
        CEP.TRC(SCCGWA, BPCSMBRT.UPD_DATA[1-1].RATE_ID);
        CEP.TRC(SCCGWA, BPCSMBRT);
        S010_CALL_BPZSMBRT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RATEID;
        BPCOQPCD.INPUT_DATA.CODE = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID;
        S000_CALL_BPZPQPCD();
        IBS.init(SCCGWA, BPCOQRTD);
        BPCOQRTD.DATA.CCY = BPB5240_AWA_5240.BRT_TBL[1-1].CCY;
        BPCOQRTD.DATA.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
        BPCOQRTD.DATA.TENOR = "" + 999;
        JIBS_tmp_int = BPCOQRTD.DATA.TENOR.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCOQRTD.DATA.TENOR = "0" + BPCOQRTD.DATA.TENOR;
        BPCOQRTD.INQ_FLG = 'C';
        S000_CALL_BPZPQRTD();
        if (BPCOQRTD.DATA.RATE_ID.trim().length() > 0) {
            if (!BPB5240_AWA_5240.BRT_TBL[1-1].RATEID.equalsIgnoreCase(BPCOQRTD.DATA.RATE_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_ERROR;
                WS_FLD_NO = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            IBS.init(SCCGWA, BPCOQRTD);
            CEP.TRC(SCCGWA, BPCOQRTD);
            BPCOQRTD.DATA.RATE_ID = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID;
            BPCOQRTD.INQ_FLG = 'R';
            CEP.TRC(SCCGWA, BPCOQRTD);
            S000_CALL_BPZPQRTD();
            CEP.TRC(SCCGWA, "END CHECK");
            if (BPCOQRTD.DATA.CCY.trim().length() > 0 
                && BPCOQRTD.DATA.BASE_TYP.trim().length() > 0) {
                CEP.TRC(SCCGWA, "EXIST");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_ID_EXIST;
                WS_FLD_NO = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "END CHECK2");
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZPQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-RTID", BPCOQRTD);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5240_AWA_5240.BRT_TBL[1-1].RATEID_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S010_CALL_BPZSMBRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BASE-RATE", BPCSMBRT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
