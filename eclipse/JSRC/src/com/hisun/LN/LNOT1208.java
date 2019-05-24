package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT1208 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CD = "LNW03";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    LNCOGLMT LNCOGLMT = new LNCOGLMT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCACLCM BPCACLCM = new BPCACLCM();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCACLGU BPCACLGU = new BPCACLGU();
    SCCGWA SCCGWA;
    LNB1208_AWA_1208 LNB1208_AWA_1208;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT1208 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB1208_AWA_1208>");
        LNB1208_AWA_1208 = (LNB1208_AWA_1208) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_INQ_GLMASTER();
        if (pgmRtn) return;
        B300_TRANS_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB1208_AWA_1208.CTA_TYPE.trim().length() == 0) {
            LNB1208_AWA_1208.CTA_TYPE = "CLDD";
        }
        if (LNB1208_AWA_1208.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT;
            WS_FLD_NO = LNB1208_AWA_1208.PROD_CD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB1208_AWA_1208.BK_BR == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT;
            WS_FLD_NO = LNB1208_AWA_1208.BK_BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_INQ_GLMASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACLCM);
        IBS.init(SCCGWA, BPCACLDD);
        IBS.init(SCCGWA, BPCACLGU);
        CEP.TRC(SCCGWA, "YYY-TEST1");
        CEP.TRC(SCCGWA, LNB1208_AWA_1208.CTA_TYPE);
        CEP.TRC(SCCGWA, LNB1208_AWA_1208.PROD_CD);
        CEP.TRC(SCCGWA, LNB1208_AWA_1208.BK_BR);
        CEP.TRC(SCCGWA, "YYY-TEST2");
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = LNB1208_AWA_1208.CTA_TYPE;
        BPCQCNGL.DAT.INPUT.BR = LNB1208_AWA_1208.BK_BR;
        if (LNB1208_AWA_1208.CTA_TYPE.equalsIgnoreCase("CLM")) {
            BPCACLCM.PROD_CD = LNB1208_AWA_1208.PROD_CD;
            CEP.TRC(SCCGWA, BPCACLCM.PROD_CD);
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 14;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLCM;
        }
        if (LNB1208_AWA_1208.CTA_TYPE.equalsIgnoreCase("CLDD")) {
            BPCACLDD.PROD_CD = LNB1208_AWA_1208.PROD_CD;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
        }
        if (LNB1208_AWA_1208.CTA_TYPE.equalsIgnoreCase("LGU")) {
            BPCACLGU.PROD_CD = LNB1208_AWA_1208.PROD_CD;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 14;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLGU;
        }
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BR);
        CEP.TRC(SCCGWA, LNB1208_AWA_1208.PROD_CD);
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B300_TRANS_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCOGLMT);
        LNCOGLMT.GM_BR = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        LNCOGLMT.GM_HO = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        LNCOGLMT.GM_IA = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        LNCOGLMT.GM_UN = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = LNCOGLMT;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD, true);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL, true);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
