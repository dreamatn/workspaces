package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9127 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPOT9127_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9127_WS_TEMP_VARIABLE();
    BPOT9127_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT9127_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICCUST CICCUST = new CICCUST();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCAFEES BPCAFEES = new BPCAFEES();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCCGCT BPCCGCT = new BPCCGCT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPB9120_AWA_9120 BPB9120_AWA_9120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9127 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9120_AWA_9120>");
        BPB9120_AWA_9120 = (BPB9120_AWA_9120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CI_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.PRDT_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REL_CTTP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.TXN_CCY);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FEE_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FUNC);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CNTR_TYP);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_DETAL_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GET_DETAL_INFO() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_FUNC_FLAG = BPB9120_AWA_9120.FUNC;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_FUNC_FLAG);
        WS_OUTPUT_DATA.WS_CI_DATA.WS_OIC_BR = 532818;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CI_DATA.WS_OIC_BR);
        if (WS_TEMP_VARIABLE.WS_FUNC_FLAG == 'P') {
            if (BPB9120_AWA_9120.PRDT_TYP.trim().length() > 0 
                && !BPB9120_AWA_9120.PRDT_TYP.equalsIgnoreCase("0")) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = BPB9120_AWA_9120.PRDT_TYP;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
                if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase("FEE")) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CONT_NOT_MATCH, WS_TEMP_VARIABLE.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCQCNGL);
                IBS.init(SCCGWA, BPCAFEES);
                BPCQCNGL.DAT.INPUT.CNTR_TYPE = "FEE";
                BPCAFEES.PROD_TYPE = BPB9120_AWA_9120.PRDT_TYP;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAFEES;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 10;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
                WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER1 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
                WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER2 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
                WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER3 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
                WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER4 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
            }
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER1);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER2);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER3);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PROD_DATA.WS_GL_MASTER4);
        if (WS_TEMP_VARIABLE.WS_FUNC_FLAG == 'C') {
            if (BPB9120_AWA_9120.TXN_CCY.trim().length() > 0 
                && !BPB9120_AWA_9120.TXN_CCY.equalsIgnoreCase("0")) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = BPB9120_AWA_9120.TXN_CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
                WS_OUTPUT_DATA.WS_CCY_DATA.WS_INT_BAS = BPCQCCY.DATA.CALR_STD;
            }
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CCY_DATA.WS_INT_BAS);
        if (WS_TEMP_VARIABLE.WS_FUNC_FLAG == 'C') {
            if (BPB9120_AWA_9120.FEE_TYP.trim().length() > 0 
                && !BPB9120_AWA_9120.FEE_TYP.equalsIgnoreCase("0")) {
            }
        }
        if (WS_TEMP_VARIABLE.WS_FUNC_FLAG == 'N') {
            if (BPB9120_AWA_9120.PRDT_TYP.trim().length() > 0 
                && !BPB9120_AWA_9120.PRDT_TYP.equalsIgnoreCase("0")) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = BPB9120_AWA_9120.PRDT_TYP;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase("FEE")) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CONT_NOT_MATCH, WS_TEMP_VARIABLE.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCSGSEQ);
                BPCSGSEQ.TYPE = "SEQ";
                BPCSGSEQ.CODE = "FEE";
                BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSGSEQ.RUN_MODE = 'O';
                CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
                CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
                S000_CALL_BPZSGSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
                WS_OUTPUT_DATA.WS_CTRT_DATA.REDEFINES30.WS_CTRT_SEG1 = 991319;
                WS_OUTPUT_DATA.WS_CTRT_DATA.WS_CTRT_NO = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA.WS_CTRT_DATA.REDEFINES30);
                WS_OUTPUT_DATA.WS_CTRT_DATA.REDEFINES30.WS_CTRT_SEQ = BPCSGSEQ.SEQ;
                WS_OUTPUT_DATA.WS_CTRT_DATA.WS_CTRT_NO = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA.WS_CTRT_DATA.REDEFINES30);
                CEP.TRC(SCCGWA, "THE WHOLE CTNO:");
                CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CTRT_DATA.WS_CTRT_NO);
            }
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_CTRT_DATA.WS_CTRT_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CI_NO);
        if (WS_TEMP_VARIABLE.WS_FUNC_FLAG == 'M' 
            && BPB9120_AWA_9120.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "GET CI SHORT NAME");
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB9120_AWA_9120.CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_ABBR_NAME);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CTRT_NO);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = "BPS01";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 113;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZGCTNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-CTNO", BPCCGCT);
        if (BPCCGCT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGCT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
