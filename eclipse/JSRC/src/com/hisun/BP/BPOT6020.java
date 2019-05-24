package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6020 {
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String K_PRDT_FEE_MAINT = "BP-S-CHG-FEE";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCFSCHG BPCFSCHG = new BPCFSCHG();
    SCCGWA SCCGWA;
    BPB6020_AWA_6020 BPB6020_AWA_6020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6020_AWA_6020>");
        BPB6020_AWA_6020 = (BPB6020_AWA_6020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSMPRD);
        IBS.init(SCCGWA, BPCFSCHG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
        B400_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPRD);
        BPCSMPRD.PRDT_CODE = BPB6020_AWA_6020.PRD_CODE;
        CEP.TRC(SCCGWA, BPB6020_AWA_6020.PRD_CODE);
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_CODE);
        BPCSMPRD.LEVEL1 = BPB6020_AWA_6020.LINE;
        BPCSMPRD.LEVEL2 = BPB6020_AWA_6020.PRO_TYP;
        CEP.TRC(SCCGWA, BPB6020_AWA_6020.LINE);
        CEP.TRC(SCCGWA, BPB6020_AWA_6020.PRO_TYP);
        BPCSMPRD.BUSI_TYPE = BPB6020_AWA_6020.PRD_TYPE;
        CEP.TRC(SCCGWA, BPB6020_AWA_6020.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCSMPRD.BUSI_TYPE);
        BPCSMPRD.PRDT_MODEL = BPB6020_AWA_6020.PRD_MODE;
        CEP.TRC(SCCGWA, BPB6020_AWA_6020.PRD_MODE);
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_MODEL);
        BPCSMPRD.STS = BPB6020_AWA_6020.PRD_STS;
        BPCSMPRD.COMB_PRDT_IND = BPB6020_AWA_6020.PRD_IND;
        BPCSMPRD.SUB_PRDT_IND = BPB6020_AWA_6020.SUBP_IND;
        BPCSMPRD.CUS_COM_FLG = BPB6020_AWA_6020.CUS_COM;
        BPCSMPRD.CUS_PER_FLG = BPB6020_AWA_6020.CUS_PER;
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_CODE);
        BPCSMPRD.INFO.FUNC = 'B';
        S000_CALL_BPZSMPRD();
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 6021;
        S000_SET_SUBS_TRN();
    }
    public void B400_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSCHG);
        BPCFSCHG.TEST = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        S000_CALL_BPZFSCHG();
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_PRDT_INF_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPRD;
        SCCCALL.ERR_FLDNO = BPB6020_AWA_6020.PRD_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB6020_AWA_6020.PRD_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFSCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_FEE_MAINT, BPCFSCHG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
