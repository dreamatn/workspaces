package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPDT {
    int JIBS_tmp_int;
    String CPN_PDTP_MT = "BP-R-MAINT-PRD-TYPE ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String PAMC_BIG_KD = "PBG01";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPCRMPDT BPCRMPDT = new BPCRMPDT();
    SCCGWA SCCGWA;
    BPCPQPDT BPCPQPDT;
    public void MP(SCCGWA SCCGWA, BPCPQPDT BPCPQPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPDT = BPCPQPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQPDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQPDT.PRDT_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_MODEL, BPCPQPDT.RC);
            return;
        }
        CEP.TRC(SCCGWA, "CHECK");
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARMC);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPARMC.KEY.TYP = "PARMC";
        BPCPRMM.FUNC = '3';
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        if (PAMC_BIG_KD == null) PAMC_BIG_KD = "";
        JIBS_tmp_int = PAMC_BIG_KD.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) PAMC_BIG_KD += " ";
        BPCPARMC.KEY.CD = PAMC_BIG_KD + BPCPARMC.KEY.CD.substring(5);
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        if (BPCPQPDT.PRDT_TYPE == null) BPCPQPDT.PRDT_TYPE = "";
        JIBS_tmp_int = BPCPQPDT.PRDT_TYPE.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQPDT.PRDT_TYPE += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + BPCPQPDT.PRDT_TYPE + BPCPARMC.KEY.CD.substring(6 + BPCPQPDT.PRDT_TYPE.length() - 1);
        CEP.TRC(SCCGWA, BPCPARMC.KEY.CD);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.DAT_PTR = BPCPARMC;
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND, BPCPQPDT.RC);
            return;
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPQPDT.PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
        CEP.TRC(SCCGWA, BPCPARMC.CDESC);
        BPCPQPDT.DESC = BPCPARMC.CDESC;
        CEP.TRC(SCCGWA, BPCPARMC.DESC);
        BPCPQPDT.DESC_ENG = BPCPARMC.DESC;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPDT = ");
            CEP.TRC(SCCGWA, BPCPQPDT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
