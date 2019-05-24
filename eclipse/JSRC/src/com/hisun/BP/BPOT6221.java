package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6221 {
    char K_ERROR = 'E';
    String CPN_MAINTAIN_THOL = "BP-S-MAINTAIN-THOL";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCSTHOL BPCSTHOL = new BPCSTHOL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB6220_AWA_6220 BPB6220_AWA_6220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6221 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6220_AWA_6220>");
        BPB6220_AWA_6220 = (BPB6220_AWA_6220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_HOL_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.EFF_DT);
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.CITY_CD);
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.CNTY_CD);
        if (BPB6220_AWA_6220.CNTY_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = BPB6220_AWA_6220.CNTY_CD;
            S000_CALL_BPZIQCNT();
            if (BPCIQCNT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_INVALID;
                WS_FLD_NO = BPB6220_AWA_6220.CNTY_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB6220_AWA_6220.CITY_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCIT);
            BPCIQCIT.INPUT_DAT.CITY_CD = BPB6220_AWA_6220.CITY_CD;
            BPCIQCIT.INPUT_DAT.CNTY_CD = BPB6220_AWA_6220.CNTY_CD;
            S000_CALL_BPZIQCIT();
            CEP.TRC(SCCGWA, BPCIQCIT.RC);
            if (BPCIQCIT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_CD_NOTFND;
                WS_FLD_NO = BPB6220_AWA_6220.CITY_CD_NO;
            }
        }
    }
    public void B020_BROWSE_HOL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTHOL);
        BPCSTHOL.KEY.EFF_DT = BPB6220_AWA_6220.EFF_DT;
        BPCSTHOL.KEY.CAL_CD = BPB6220_AWA_6220.CAL_CD;
        BPCSTHOL.FUNC = 'B';
        CEP.TRC(SCCGWA, BPCSTHOL.KEY);
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        S000_CALL_BPZSTHOL();
    }
    public void S000_CALL_BPZSTHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINTAIN_THOL, BPCSTHOL);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIQCNT);
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
        CEP.TRC(SCCGWA, BPCIQCNT.RC.RC_CODE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
