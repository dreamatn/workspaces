package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSLND {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_CI_CISOBDAC = "CI-CISOBDAC";
    String CPN_CI_CIZCUST = "CI-CIZCUST";
    char WS_CCY_ID = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    String WS_FORM_CODE = " ";
    double WS_N_RATE = 0;
    double WS_PRIN_AMT = 0;
    LNZSLND_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSLND_WS_LOAN_CONT_AREA();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCULND LNCULND = new LNCULND();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    SCCGWA SCCGWA;
    LNCSLND LNCSLND;
    public void MP(SCCGWA SCCGWA, LNCSLND LNCSLND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLND = LNCSLND;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSLND.RC.RC_APP = "LN";
        LNCSLND.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_PRE_SLND_PROC();
        if (pgmRtn) return;
        B200_VALIDATE_CHECK();
        if (pgmRtn) return;
        B500_INQ_CNGL_PROC();
        if (pgmRtn) return;
        B600_DELETE_GL_MASTER_PROC();
        if (pgmRtn) return;
        B700_DELETE_LOAN_PROC();
        if (pgmRtn) return;
    }
    public void B100_PRE_SLND_PROC() throws IOException,SQLException,Exception {
        B130_GET_PRDT_CLASSIFY();
        if (pgmRtn) return;
    }
    public void B130_GET_PRDT_CLASSIFY() throws IOException,SQLException,Exception {
    }
    public void B140_GET_CI_INFO() throws IOException,SQLException,Exception {
    }
    public void B200_VALIDATE_CHECK() throws IOException,SQLException,Exception {
        B210_VALIDATE_MST();
        if (pgmRtn) return;
        B220_VALIDATE_LOAN();
        if (pgmRtn) return;
    }
    public void B210_VALIDATE_MST() throws IOException,SQLException,Exception {
        if (LNCSLND.COMM_DATA.LMT_NO != 0) {
            B210_LIMIT_YES_CHECK();
            if (pgmRtn) return;
        }
        if (LNCSLND.COMM_DATA.LMT_NO == 0) {
            B210_LIMIT_NO_CHECK();
            if (pgmRtn) return;
        }
    }
    public void B210_LIMIT_YES_CHECK() throws IOException,SQLException,Exception {
    }
    public void B210_LIMIT_NO_CHECK() throws IOException,SQLException,Exception {
    }
    public void B220_VALIDATE_LOAN() throws IOException,SQLException,Exception {
        if (LNCSLND.COMM_DATA.LMT_NO != 0) {
        }
    }
    public void B300_GENERATE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.PRD_CODE = LNCSLND.COMM_DATA.PROD_CD;
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        LNCSLND.COMM_DATA.LN_AC = BPCCGAC.DATA.AC_NO;
        CEP.TRC(SCCGWA, LNCSLND.COMM_DATA.LN_AC);
    }
    public void B400_MAINT_AC_AND_CI() throws IOException,SQLException,Exception {
    }
    public void B500_INQ_CNGL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = LNCSLND.COMM_DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACLDD);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = LNCSLND.COMM_DATA.BOOK_BR;
        BPCACLDD.PROD_CD = LNCSLND.COMM_DATA.PROD_CD;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MOD_NO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO);
    }
    public void B600_DELETE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, BPCPQAMO);
        BPCPQAMO.FUNC = 'Q';
        BPCPQAMO.DATA_INFO.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPQAMO.DATA_INFO.PROD_TYPE = LNCSLND.COMM_DATA.PROD_CD;
        BPCPQAMO.DATA_INFO.BR = LNCSLND.COMM_DATA.BOOK_BR;
        S000_CALL_BPZPQAMO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = LNCSLND.COMM_DATA.LN_AC;
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCUCNGM.PROD_TYPE = LNCSLND.COMM_DATA.PROD_CD;
        BPCUCNGM.BR = LNCSLND.COMM_DATA.BOOK_BR;
        BPCUCNGM.MOD_NO = BPCPQAMO.DATA_INFO.MOD_NO;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        BPCUCNGM.FUNC = 'D';
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void B700_DELETE_LOAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCULND);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSLND.COMM_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCULND.COMM_DATA);
        S000_CALL_LNZULND();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCULND.COMM_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLND.COMM_DATA);
    }
    public void S000_CALL_LNZULND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-LOAN-DELETE", LNCULND);
        if (LNCULND.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCULND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCGAC.RC.RC_CODE != 0) {
            LNCSLND.RC.RC_APP = BPCCGAC.RC.MMO;
            LNCSLND.RC.RC_RTNCODE = BPCCGAC.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            LNCSLND.RC.RC_APP = BPCQCNGL.RC.RC_MMO;
            LNCSLND.RC.RC_RTNCODE = BPCQCNGL.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            LNCSLND.RC.RC_APP = BPCUCNGM.RC.RC_APP;
            LNCSLND.RC.RC_RTNCODE = BPCUCNGM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQPRD.RC.RC_CODE != 0) {
            LNCSLND.RC.RC_APP = BPCPQPRD.RC.RC_AP;
            LNCSLND.RC.RC_RTNCODE = BPCPQPRD.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD", BPCPQAMO);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQAMO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_CISOBDAC() throws IOException,SQLException,Exception {
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSLND.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSLND=");
            CEP.TRC(SCCGWA, LNCSLND);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
