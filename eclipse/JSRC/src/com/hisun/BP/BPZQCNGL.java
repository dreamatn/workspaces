package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZQCNGL {
    boolean pgmRtn = false;
    char K_DEFAULT_H_CLASS = 'O';
    char K_NONSTAFF = 'N';
    String WS_PROD_TYPE_TMP = " ";
    String WS_OTH = " ";
    BPZQCNGL_REDEFINES3 REDEFINES3 = new BPZQCNGL_REDEFINES3();
    String WS_OTH2 = " ";
    BPZQCNGL_REDEFINES14 REDEFINES14 = new BPZQCNGL_REDEFINES14();
    char WS_FND_FLG = ' ';
    char WS_CNGL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCRCNGL BPCRCNGL = new BPCRCNGL();
    BPRCNGL BPRCNGL = new BPRCNGL();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCACLBA BPCACLBA = new BPCACLBA();
    BPCACLCM BPCACLCM = new BPCACLCM();
    BPCACLGU BPCACLGU = new BPCACLGU();
    BPCAFEES BPCAFEES = new BPCAFEES();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    BPCAZHLC BPCAZHLC = new BPCAZHLC();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCCNGL BPCCNGL = new BPCCNGL();
    SCCGWA SCCGWA;
    BPCQCNGL BPCQCNGL;
    BPCCNGL BPCCNGL1;
    public void MP(SCCGWA SCCGWA, BPCQCNGL BPCQCNGL, BPCCNGL BPCCNGL1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQCNGL = BPCQCNGL;
        this.BPCCNGL1 = BPCCNGL1;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQCNGL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCCNGL1 = (BPCCNGL) BPCQCNGL.DAT.INPUT.OTH_PTR;
        IBS.init(SCCGWA, BPRCNGL);
        IBS.init(SCCGWA, BPCPQPDM);
        IBS.init(SCCGWA, BPCPQAMO);
        WS_FND_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_MSTNO_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCQCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.CNTR_TYPE);
            BPCPQPDM.PRDT_MODEL = BPCQCNGL.DAT.INPUT.CNTR_TYPE;
            CEP.TRC(SCCGWA, BPCPQPDM.PRDT_MODEL);
            BPCPQPDM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_BPZPQPDM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BR);
        BPCQCNGL.DAT.INPUT.BOOK_FLG = "BK001";
        CEP.TRC(SCCGWA, "HELLO");
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BOOK_FLG);
    }
    public void B020_QUERY_MSTNO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BR);
        B022_QUERY_MSTNO1();
        if (pgmRtn) return;
        B023_QUERY_ACC_MOD_NO();
        if (pgmRtn) return;
    }
    public void B022_QUERY_MSTNO1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OTH);
        IBS.init(SCCGWA, BPRCNGL);
        WS_CNGL_FLG = ' ';
        IBS.init(SCCGWA, BPCICNGL);
        BPCRCNGL.FUNC = 'I';
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.CNTR_TYPE);
        BPRCNGL.KEY.CNTR_TYPE = BPCQCNGL.DAT.INPUT.CNTR_TYPE;
        BPRCNGL.KEY.BOOK_FLG = BPCQCNGL.DAT.INPUT.BOOK_FLG;
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BR);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BOOK_FLG);
        BPRCNGL.KEY.BR = BPCQCNGL.DAT.INPUT.BR;
        BPRCNGL.KEY.OTH = " ";
        if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("MMDP")) {
            IBS.init(SCCGWA, BPCAMMDP);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCAMMDP);
            BPCICNGL.PROD_TYPE = BPCAMMDP.PROD_TYPE;
            BPCICNGL.CI_TYPE = BPCAMMDP.CI_TYPE;
            BPCICNGL.FIN_TYP = BPCAMMDP.FIN_TYP;
            BPCICNGL.AC_TYP = BPCAMMDP.AC_TYP;
            BPCICNGL.PROP_TYP = BPCAMMDP.PROP_TYP;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("CAAC")) {
            IBS.init(SCCGWA, BPCACAAC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCACAAC);
            BPCICNGL.PROD_TYPE = BPCACAAC.PROD_TYPE;
            BPCICNGL.CI_TYPE = BPCACAAC.CI_TYPE;
            BPCICNGL.FIN_TYP = BPCACAAC.FIN_TYP;
            BPCICNGL.AC_TYP = BPCACAAC.AC_TYP;
            BPCICNGL.PROP_TYP = BPCACAAC.PROP_TYP;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("CLDD")) {
            IBS.init(SCCGWA, BPCACLDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCACLDD);
            BPCICNGL.PROD_TYPE = BPCACLDD.PROD_CD;
            BPCICNGL.TERM_CD = BPCACLDD.TERM_CD;
            BPCICNGL.LOAN_TYPE = BPCACLDD.LOAN_TYPE;
            BPCICNGL.WE_FLG = BPCACLDD.WE_FLG;
            BPCICNGL.DUTY_FREE = BPCACLDD.DUTY_FREE;
            BPCICNGL.FIN_TYP = BPCACLDD.FIN_TYP;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("CLCM")) {
            IBS.init(SCCGWA, BPCACLCM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCACLCM);
            BPCICNGL.PROD_TYPE = BPCACLCM.PROD_CD;
            BPCICNGL.LOAN_TYPE = BPCACLCM.LOAN_TYPE;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("CLGU")) {
            IBS.init(SCCGWA, BPCACLGU);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCACLGU);
            BPCICNGL.PROD_TYPE = BPCACLGU.PROD_CD;
            BPCICNGL.LOAN_TYPE = BPCACLGU.LOAN_TYPE;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("CLBA")) {
            IBS.init(SCCGWA, BPCACLBA);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCACLBA);
            BPCICNGL.PROD_TYPE = BPCACLBA.PROD_CD;
            BPCICNGL.LOAN_TYPE = BPCACLBA.LOAN_TYPE;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("FEES")) {
            CEP.TRC(SCCGWA, "FEES CNGL");
            IBS.init(SCCGWA, BPCAFEES);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCAFEES);
            BPCICNGL.PROD_TYPE = BPCAFEES.PROD_TYPE;
        } else if (BPCQCNGL.DAT.INPUT.CNTR_TYPE.equalsIgnoreCase("ZHLC")) {
            IBS.init(SCCGWA, BPCAZHLC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCAZHLC);
            BPCICNGL.PROD_TYPE = BPCAZHLC.PROD_TYPE;
            BPCICNGL.CI_TYPE = BPCAZHLC.CI_TYPE;
            BPCICNGL.FIN_TYP = BPCAZHLC.FIN_TYP;
        } else {
            CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.OTH_PTR_LEN);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCNGL1);
