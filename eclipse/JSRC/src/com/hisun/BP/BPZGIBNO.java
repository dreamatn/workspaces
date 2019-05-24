package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGIBNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZGACNO";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String K_CMP_CALL_BPZSGSEQ = "BP-S-GET-SEQ   ";
    String K_CMP_CALL_BPZSGOBL = "BP-S-GET-OBL   ";
    String K_CMP_CALL_SCZKDGAC = "SC-AC-DIGIT    ";
    BPZGIBNO_WS_MSGID WS_MSGID = new BPZGIBNO_WS_MSGID();
    short WS_CNT = 0;
    short WS_I = 0;
    String WS_BR = " ";
    char WS_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCGWA SCCGWA;
    BPCCGIB BPCCGIB;
    public void MP(SCCGWA SCCGWA, BPCCGIB BPCCGIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCGIB = BPCCGIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGIBNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GEN_IBNO_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GEN_IBNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "ACNO";
        BPCSGSEQ.CODE = "IBNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (BPCCGIB.IBNO == null) BPCCGIB.IBNO = "";
        JIBS_tmp_int = BPCCGIB.IBNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPCCGIB.IBNO += " ";
        JIBS_tmp_str[0] = "" + 955087;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCCGIB.IBNO = JIBS_tmp_str[0] + BPCCGIB.IBNO.substring(6);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (BPCCGIB.IBNO == null) BPCCGIB.IBNO = "";
        JIBS_tmp_int = BPCCGIB.IBNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPCCGIB.IBNO += " ";
        BPCCGIB.IBNO = BPCCGIB.IBNO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1) + BPCCGIB.IBNO.substring(7 + 8 - 1);
        CEP.TRC(SCCGWA, BPCCGIB.IBNO);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CHECK_RETURN_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGIB.RC);
        if (BPCCGIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCGIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCGIB = ");
            CEP.TRC(SCCGWA, BPCCGIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
