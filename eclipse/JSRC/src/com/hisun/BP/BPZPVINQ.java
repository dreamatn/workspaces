package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVINQ {
    BPRVWA_VCH_AREA VCH_AREA;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    Object WS_VCH_PTR;
    char WS_FST_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVWA BPRVWA = new BPRVWA();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPCOVABS BPCOVABS = new BPCOVABS();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCOVAPT BPCOVAPT = new BPCOVAPT();
    BPCTVCHT BPCTVCHT = new BPCTVCHT();
    BPCTVCHH BPCTVCHH = new BPCTVCHH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOVINQ BPCOVINQ;
    public void MP(SCCGWA SCCGWA, BPCOVINQ BPCOVINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVINQ = BPCOVINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVINQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOVINQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOVINQ.FUNC_CD == 'Q') {
            WS_VCH_PTR = BPCOVINQ.VCH_PTR;
        } else if (BPCOVINQ.FUNC_CD == 'P') {
            WS_VCH_PTR = BPRVWA;
            IBS.init(SCCGWA, BPRVWA.BASIC_AREA);
            BPRVWA.BASIC_AREA.CNT = 0;
            VCH_AREA = new BPRVWA_VCH_AREA();
            BPRVWA.VCH_AREA.add(VCH_AREA);
        }
        B010_VCH_PROCESS();
        if (pgmRtn) return;
        if (BPCOVINQ.FUNC_CD == 'P') {
            IBS.init(SCCGWA, BPCOVAPT);
            BPCOVAPT.VCH_PTR = WS_VCH_PTR;
            S000_CALL_BPZPVAPT();
            if (pgmRtn) return;
        }
    }
    public void B010_VCH_PROCESS() throws IOException,SQLException,Exception {
        WS_FST_FLG = 'Y';
        IBS.init(SCCGWA, BPRVCHT);
        IBS.init(SCCGWA, BPCTVCHT);
        BPCTVCHT.INFO.POINTER = BPRVCHT;
        BPCTVCHT.INFO.FUNC = 'B';
        BPCTVCHT.INFO.OPT = 'S';
        BPCTVCHT.INFO.INDEX_FLG = '2';
        BPRVCHT.KEY.AC_DATE = BPCOVINQ.AC_DATE;
        BPRVCHT.JRN_NO = BPCOVINQ.JRN_NO;
        S000_CALL_BPZTVCHT();
        if (pgmRtn) return;
        BPCTVCHT.INFO.OPT = 'N';
        S000_CALL_BPZTVCHT();
        if (pgmRtn) return;
        if (BPCTVCHT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORIG_VCH_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (BPCTVCHT.RETURN_INFO != 'N') {
            B011_ADD_VWA();
            if (pgmRtn) return;
            BPCTVCHT.INFO.FUNC = 'B';
            BPCTVCHT.INFO.OPT = 'N';
            S000_CALL_BPZTVCHT();
            if (pgmRtn) return;
        }
        BPCTVCHT.INFO.FUNC = 'B';
        BPCTVCHT.INFO.OPT = 'E';
        S000_CALL_BPZTVCHT();
        if (pgmRtn) return;
    }
    public void B011_ADD_VWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOVAWR);
        BPCOVAWR.CONTROL.VCH_IND = 'Y';
        BPCOVAWR.CONTROL.VCH_PTR = WS_VCH_PTR;
        if (WS_FST_FLG == 'Y') {
            BPCOVAWR.FST_FLG = 'Y';
            WS_FST_FLG = 'N';
            IBS.init(SCCGWA, BPCOVABS);
            BPCOVABS.TR_TYPE = BPRVCHT.TR_TYPE;
            BPCOVABS.ODE_FLG = BPRVCHT.ODE_FLG;
            BPCOVABS.ODE_GRP_NO = BPRVCHT.ODE_GRP_NO;
            BPCOVABS.OTHSYS_KEY = BPRVCHT.OTHSYS_KEY;
            BPCOVABS.REDEFINES19.VH_IND = 'Y';
            BPCOVABS.REDEFINES19.VH_PTR = WS_VCH_PTR;
            S000_CALL_BPZPVABS();
            if (pgmRtn) return;
        } else {
            BPCOVAWR.FST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.REDEFINES17.VH_IND);
        BPCOVAWR.PARTB.BOOK_FLG = BPRVCHT.BOOK_FLG;
        BPCOVAWR.PARTB.BR = BPRVCHT.BR;
        BPCOVAWR.PARTB.ITM = BPRVCHT.ITM;
        BPCOVAWR.PARTB.CCY = BPRVCHT.CCY;
        BPCOVAWR.PARTB.SIGN = BPRVCHT.SIGN;
        BPCOVAWR.PARTB.AMT = BPRVCHT.AMT;
        BPCOVAWR.PARTB.VAL_DATE = BPRVCHT.VAL_DATE;
        BPCOVAWR.PARTB.CNTR_TYPE = BPRVCHT.CNTR_TYPE;
        BPCOVAWR.PARTB.PROD_CODE = BPRVCHT.PROD_CODE;
        BPCOVAWR.PARTB.AC_NO = BPRVCHT.AC_NO;
        BPCOVAWR.PARTB.EVENT_CODE = BPRVCHT.EVENT_CODE;
        BPCOVAWR.PARTB.GLMST = BPRVCHT.GLMST;
        BPCOVAWR.PARTB.CNTR_TYPE_REL = BPRVCHT.CNTR_TYPE_REL;
        BPCOVAWR.PARTB.PROD_CODE_REL = BPRVCHT.PROD_CODE_REL;
        BPCOVAWR.PARTB.AC_NO_REL = BPRVCHT.AC_NO_REL;
        BPCOVAWR.PARTB.EVENT_CODE_REL = BPRVCHT.EVENT_CODE_REL;
        BPCOVAWR.PARTB.GLMST_REL = BPRVCHT.GLMST_REL;
        BPCOVAWR.PARTB.CI_NO = BPRVCHT.CI_NO;
        BPCOVAWR.PARTB.REF_NO = BPRVCHT.REF_NO;
        BPCOVAWR.PARTB.TR_GUP = BPRVCHT.TR_GUP;
        BPCOVAWR.PARTB.CHQ_NO = BPRVCHT.CHQ_NO;
        BPCOVAWR.PARTB.POST_DATE = BPRVCHT.POST_DATE;
        BPCOVAWR.PARTB.POST_NARR = BPRVCHT.POST_NARR;
        BPCOVAWR.PARTB.NARR_CD = BPRVCHT.NARR_CD;
        BPCOVAWR.PARTB.DESC = BPRVCHT.DESC;
        BPCOVAWR.PARTB.EFF_DAYS = BPRVCHT.EFF_DAYS;
        BPCOVAWR.PARTB.PRINT_FLG = BPRVCHT.PRINT_FLG;
        BPCOVAWR.PARTB.SUSPENSE_FLG = BPRVCHT.SUSPENSE_FLG;
        BPCOVAWR.PARTB.SUSPENSE_RSN = BPRVCHT.SUSPENSE_RSN;
        BPCOVAWR.PARTB.FLR = BPRVCHT.FLR;
        if (BPCOVAWR.PARTB.FLR == null) BPCOVAWR.PARTB.FLR = "";
        JIBS_tmp_int = BPCOVAWR.PARTB.FLR.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCOVAWR.PARTB.FLR += " ";
        BPCOVAWR.PARTB.FLR = BPCOVAWR.PARTB.FLR.substring(0, 5 - 1) + "Q" + BPCOVAWR.PARTB.FLR.substring(5 + 1 - 1);
        if (BPCOVAWR.PARTB.FLR == null) BPCOVAWR.PARTB.FLR = "";
        JIBS_tmp_int = BPCOVAWR.PARTB.FLR.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCOVAWR.PARTB.FLR += " ";
        JIBS_tmp_str[0] = "" + BPRVCHT.KEY.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCOVAWR.PARTB.FLR = BPCOVAWR.PARTB.FLR.substring(0, 6 - 1) + JIBS_tmp_str[0] + BPCOVAWR.PARTB.FLR.substring(6 + 8 - 1);
        S000_CALL_BPZPVAWR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPVABS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-BASIC-ADD", BPCOVABS);
        if (BPCOVABS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVABS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVAWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-WRITE", BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVAPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-PRINT", BPCOVAPT);
        if (BPCOVAPT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAPT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTVCHT() throws IOException,SQLException,Exception {
        if (BPCOVINQ.AC_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CALLCPN(SCCGWA, "BP-R-MAINT-VCHT", BPCTVCHT);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTVCHT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCTVCHH);
            IBS.CALLCPN(SCCGWA, "BP-R-MAINT-VCHH", BPCTVCHH);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTVCHH);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCTVCHT);
        }
        if (BPCTVCHT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTVCHT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVINQ.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVINQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVINQ = ");
            CEP.TRC(SCCGWA, BPCOVINQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
