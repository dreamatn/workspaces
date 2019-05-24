package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGCTNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    BPZGCTNO_WS_MSGID WS_MSGID = new BPZGCTNO_WS_MSGID();
    short WS_I = 0;
    String WS_CODE = " ";
    String WS_SEQ_CODE = " ";
    char WS_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCKDGCI SCCKDGCI = new SCCKDGCI();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    BPCQHSEQ BPCQHSEQ = new BPCQHSEQ();
    BPCRHSEQ BPCRHSEQ = new BPCRHSEQ();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCGCT BPCCGCT;
    public void MP(SCCGWA SCCGWA, BPCCGCT BPCCGCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCGCT = BPCCGCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGCTNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, SCCKDGCI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_CTNO_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGCT.BR);
        CEP.TRC(SCCGWA, BPCCGCT.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCCGCT.CTNO);
        CEP.TRC(SCCGWA, BPCCGCT.CTNO_TYPE);
        CEP.TRC(SCCGWA, BPCCGCT.SPEC_PREFIX);
    }
    public void B020_GET_CTNO_PROC() throws IOException,SQLException,Exception {
        if (BPCCGCT.SPEC_PREFIX.trim().length() == 0) {
            CEP.TRC(SCCGWA, "NORMAL PREFIX CONTRACT NO");
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCCGCT.PRDT_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
            if (WS_SEQ_CODE == null) WS_SEQ_CODE = "";
            JIBS_tmp_int = WS_SEQ_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_SEQ_CODE += " ";
            WS_SEQ_CODE = "CTNO" + WS_SEQ_CODE.substring(4);
            if (WS_SEQ_CODE == null) WS_SEQ_CODE = "";
            JIBS_tmp_int = WS_SEQ_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_SEQ_CODE += " ";
            if (BPCPQPRD.AC_TYPE == null) BPCPQPRD.AC_TYPE = "";
            JIBS_tmp_int = BPCPQPRD.AC_TYPE.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQPRD.AC_TYPE += " ";
            WS_SEQ_CODE = WS_SEQ_CODE.substring(0, 5 - 1) + BPCPQPRD.AC_TYPE + WS_SEQ_CODE.substring(5 + 2 - 1);
            CEP.TRC(SCCGWA, WS_SEQ_CODE);
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = "CTNO";
            BPCSGSEQ.CODE = WS_SEQ_CODE;
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
            CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            BPCCGCT.CTNO = "70" + BPCCGCT.CTNO.substring(2);
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            if (BPCCGCT.BR == null) BPCCGCT.BR = "";
            JIBS_tmp_int = BPCCGCT.BR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.BR += " ";
            BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 3 - 1) + BPCCGCT.BR + BPCCGCT.CTNO.substring(3 + 3 - 1);
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            if (BPCPQPRD.AC_TYPE == null) BPCPQPRD.AC_TYPE = "";
            JIBS_tmp_int = BPCPQPRD.AC_TYPE.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPQPRD.AC_TYPE += " ";
            BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 6 - 1) + BPCPQPRD.AC_TYPE + BPCCGCT.CTNO.substring(6 + 2 - 1);
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("68")) {
                JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
                JIBS_tmp_int = BPCCGCT.CTNO.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
                BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 8 - 1) + JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1) + BPCCGCT.CTNO.substring(8 + 5 - 1);
                if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
                JIBS_tmp_int = BPCCGCT.CTNO.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
                JIBS_tmp_str[0] = "" + 0;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 13 - 1) + JIBS_tmp_str[0] + BPCCGCT.CTNO.substring(13 + 4 - 1);
            } else if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("66")) {
                JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
                JIBS_tmp_int = BPCCGCT.CTNO.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
                BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 8 - 1) + JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1) + BPCCGCT.CTNO.substring(8 + 4 - 1);
                if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
                JIBS_tmp_int = BPCCGCT.CTNO.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
                JIBS_tmp_str[0] = "" + 0;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPCCGCT.CTNO.substring(12 + 5 - 1);
            }
            CEP.TRC(SCCGWA, "THE WHOLE CTNO:");
            CEP.TRC(SCCGWA, BPCCGCT.CTNO);
        } else {
            CEP.TRC(SCCGWA, "SPCECIAL PREFIX CONTRACT NO");
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = "CTNO";
            if (BPCSGSEQ.CODE == null) BPCSGSEQ.CODE = "";
            JIBS_tmp_int = BPCSGSEQ.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCSGSEQ.CODE += " ";
            if (BPCCGCT.BR == null) BPCCGCT.BR = "";
            JIBS_tmp_int = BPCCGCT.BR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.BR += " ";
            BPCSGSEQ.CODE = BPCCGCT.BR + BPCSGSEQ.CODE.substring(3);
            if (BPCSGSEQ.CODE == null) BPCSGSEQ.CODE = "";
            JIBS_tmp_int = BPCSGSEQ.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCSGSEQ.CODE += " ";
            if (BPCCGCT.SPEC_PREFIX == null) BPCCGCT.SPEC_PREFIX = "";
            JIBS_tmp_int = BPCCGCT.SPEC_PREFIX.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.SPEC_PREFIX += " ";
            BPCSGSEQ.CODE = BPCSGSEQ.CODE.substring(0, 4 - 1) + BPCCGCT.SPEC_PREFIX + BPCSGSEQ.CODE.substring(4 + 2 - 1);
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
            CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            if (BPCCGCT.BR == null) BPCCGCT.BR = "";
            JIBS_tmp_int = BPCCGCT.BR.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.BR += " ";
            BPCCGCT.CTNO = BPCCGCT.BR + BPCCGCT.CTNO.substring(3);
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            if (BPCCGCT.SPEC_PREFIX == null) BPCCGCT.SPEC_PREFIX = "";
            JIBS_tmp_int = BPCCGCT.SPEC_PREFIX.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.SPEC_PREFIX += " ";
            BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 4 - 1) + BPCCGCT.SPEC_PREFIX + BPCCGCT.CTNO.substring(4 + 2 - 1);
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (BPCCGCT.CTNO == null) BPCCGCT.CTNO = "";
            JIBS_tmp_int = BPCCGCT.CTNO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPCCGCT.CTNO += " ";
            BPCCGCT.CTNO = BPCCGCT.CTNO.substring(0, 6 - 1) + JIBS_tmp_str[0].substring(6 - 1, 6 + 10 - 1) + BPCCGCT.CTNO.substring(6 + 10 - 1);
            CEP.TRC(SCCGWA, "THE WHOLE CTNO:");
            CEP.TRC(SCCGWA, BPCCGCT.CTNO);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
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
        if (BPCCGCT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCGCT = ");
            CEP.TRC(SCCGWA, BPCCGCT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
