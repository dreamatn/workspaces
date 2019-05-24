package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQTRT {
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "TRT  INFOMATION MAINTAIN";
    String K_CPY_BPRPTRTM = "BPRPTRTM";
    String WS_ERR_MSG = " ";
    short WS_COUNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTRT BPRTRT = new BPRTRT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCPQTRT BPCPQTRT;
    public void MP(SCCGWA SCCGWA, BPCPQTRT BPCPQTRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQTRT = BPCPQTRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQTRT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQTRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQTRT.CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, BPCPQTRT.RC);
            return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRT);
        IBS.init(SCCGWA, BPCPRMM);
        BPRTRT.KEY.TYP = "TRT  ";
        BPCPRMM.FUNC = '3';
        BPRTRT.KEY.CD = BPCPQTRT.CD;
        BPCPRMM.EFF_DT = BPCPQTRT.EFF_DT;
        BPCPRMM.DAT_PTR = BPRTRT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQTRT.RC);
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPQTRT.CD = BPRTRT.KEY.CD;
        BPCPQTRT.EFF_DT = BPCPRMM.EFF_DT;
        BPCPQTRT.EXP_DT = BPCPRMM.EXP_DT;
        BPCPQTRT.TR_MMO = BPRTRT.DATA_TXT.TR_MMO;
        BPCPQTRT.STUS = BPRTRT.DATA_TXT.STUS;
        BPCPQTRT.SYS_LVL = BPRTRT.DATA_TXT.SYS_LVL;
        BPCPQTRT.RUN_MODE = BPRTRT.DATA_TXT.RUN_MODE;
        BPCPQTRT.LOG_IND = BPRTRT.DATA_TXT.LOG_IND;
        BPCPQTRT.REEN_IND = BPRTRT.DATA_TXT.REEN_IND;
        BPCPQTRT.CLS = BPRTRT.DATA_TXT.CLS;
        BPCPQTRT.SELF_GRNT = BPRTRT.DATA_TXT.SELF_GRNT;
        BPCPQTRT.AUTH_LVL = BPRTRT.DATA_TXT.AUTH_LVL;
        BPCPQTRT.APVL_IND = BPRTRT.DATA_TXT.APVL_IND;
        BPCPQTRT.HIS_REC_IND = BPRTRT.DATA_TXT.HIS_REC_IND;
        BPCPQTRT.ATTR_WORD = BPRTRT.DATA_TXT.ATTR_WORD;
        BPCPQTRT.SUBS_TX_CODE = BPRTRT.DATA_TXT.SUBS_TX_CODE;
        BPCPQTRT.AWA_IND = BPRTRT.DATA_TXT.AWA_IND;
        BPCPQTRT.INP_FMT = BPRTRT.DATA_TXT.INP_FMT;
        BPCPQTRT.OUTP_FMT = BPRTRT.DATA_TXT.OUTP_FMT;
        BPCPQTRT.OUTP_CNTL = BPRTRT.DATA_TXT.OUTP_CNTL;
        BPCPQTRT.SPEC_CNTL = BPRTRT.DATA_TXT.SPEC_CNTL;
        BPCPQTRT.PGM_CNT = BPRTRT.DATA_TXT.PGM_CNT;
        for (WS_COUNT = 1; WS_COUNT <= 5; WS_COUNT += 1) {
            BPCPQTRT.PGM[WS_COUNT-1].PGM_NAME = BPRTRT.DATA_TXT.PGM[WS_COUNT-1].PGM_NAME;
            BPCPQTRT.PGM[WS_COUNT-1].PGM_SCHE = BPRTRT.DATA_TXT.PGM[WS_COUNT-1].PGM_SCHE;
            BPCPQTRT.PGM[WS_COUNT-1].PGM_CTL = BPRTRT.DATA_TXT.PGM[WS_COUNT-1].PGM_CTL;
            BPCPQTRT.PGM[WS_COUNT-1].PGM_RMK = BPRTRT.DATA_TXT.PGM[WS_COUNT-1].PGM_RMK;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQTRT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQTRT = ");
            CEP.TRC(SCCGWA, BPCPQTRT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
