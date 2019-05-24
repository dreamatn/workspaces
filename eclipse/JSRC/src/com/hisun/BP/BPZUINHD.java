package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUINHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_PROC_NHISD = "BP-R-PROC-NHISD";
    String CPN_R_PROC_NHIST = "BP-R-PROC-NHIST";
    String CPN_P_INQ_PC = "BP-P-INQ-PC";
    String K_PUBCD_HISCD = "HISCD";
    String K_OUTPUT_FMT = "BP001";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TMP_DATE = " ";
    String WS_TMP_TIME = " ";
    BPZUINHD_WS_TS_FIELD WS_TS_FIELD = new BPZUINHD_WS_TS_FIELD();
    BPZUINHD_WS_TS WS_TS = new BPZUINHD_WS_TS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRNHISD BPRNHISD = new BPRNHISD();
    BPCTHISD BPCTHISD = new BPCTHISD();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCFMT SCCFMT = new SCCFMT();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCUINHD BPCUINHD;
    public void MP(SCCGWA SCCGWA, BPCUINHD BPCUINHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUINHD = BPCUINHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUINHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUINHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B040_READ_NHSD_RECORD();
            if (pgmRtn) return;
        } else {
            B020_BROWSE_NHSD_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B040_READ_NHSD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTHIST);
        IBS.init(SCCGWA, BPRNHIST);
        BPCTHIST.INFO.FUNC = '4';
        BPRNHIST.KEY.JRNNO = BPCUINHD.INPUT.JRNNO;
        BPRNHIST.KEY.JRN_SEQ = BPCUINHD.INPUT.JRN_SEQ;
        BPRNHIST.KEY.AC_DT = BPCUINHD.INPUT.AC_DT;
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.AC_DT);
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        CEP.TRC(SCCGWA, BPCTHIST.LEN);
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        if (BPCTHIST.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_OUTPUT_BPRNHIST_CN();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_NHSD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTHIST);
        IBS.init(SCCGWA, BPRNHIST);
        BPCTHIST.INFO.FUNC = '4';
        BPRNHIST.KEY.JRNNO = BPCUINHD.INPUT.JRNNO;
        BPRNHIST.KEY.JRN_SEQ = BPCUINHD.INPUT.JRN_SEQ;
        BPRNHIST.KEY.AC_DT = BPCUINHD.INPUT.AC_DT;
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCUINHD.INPUT.AC_DT);
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        if (BPCTHIST.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_OUTPUT_BPRNHIST_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTHISD);
        BPCTHISD.INFO.FUNC = '1';
        BPCTHISD.INFO.JRNNO = BPCUINHD.INPUT.JRNNO;
        BPCTHISD.INFO.JRN_SEQ = BPCUINHD.INPUT.JRN_SEQ;
        BPCTHISD.INFO.AC_DT = BPCUINHD.INPUT.AC_DT;
        BPCTHISD.PTR = BPRNHISD;
        BPCTHISD.LEN = 331;
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
        BPCTHISD.INFO.FUNC = '2';
        BPCTHISD.PTR = BPRNHISD;
        BPCTHISD.LEN = 331;
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
        while (BPCTHISD.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_FORMAT_OUTPUT_DATA();
            if (pgmRtn) return;
            R000_WRITE_PAGE_RECORD();
            if (pgmRtn) return;
            BPCTHISD.PTR = BPRNHISD;
            BPCTHISD.INFO.FUNC = '2';
            BPCTHISD.LEN = 331;
            S000_CALL_BPZTHISD();
            if (pgmRtn) return;
        }
        BPCTHISD.INFO.FUNC = '3';
        BPCTHISD.PTR = BPRNHISD;
        BPCTHISD.LEN = 331;
        S000_CALL_BPZTHISD();
        if (pgmRtn) return;
    }
    public void R000_FORMAT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_TS.WS_TS_QUEUE.WS_FLD_CNAME = BPRNHISD.FLD_CDESC;
        WS_TS.WS_TS_QUEUE.WS_FLD_ENAME = BPRNHISD.FLD_EDESC;
        WS_TS.WS_TS_QUEUE.WS_OLD_DAT = BPRNHISD.FLD_DAT_OLD;
        WS_TS.WS_TS_QUEUE.WS_NEW_DAT = BPRNHISD.FLD_DAT_NEW;
        CEP.TRC(SCCGWA, WS_TS.WS_TS_QUEUE.WS_FLD_CNAME);
        CEP.TRC(SCCGWA, WS_TS.WS_TS_QUEUE.WS_FLD_ENAME);
    }
    public void R000_OUTPUT_BPRNHIST_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS.WS_OUTPUT_FMT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 4087;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS.WS_OUTPUT_FMT.WS_AC_DT = BPRNHIST.KEY.AC_DT;
        WS_TS.WS_OUTPUT_FMT.WS_JRNNO = BPRNHIST.KEY.JRNNO;
        WS_TS.WS_OUTPUT_FMT.WS_JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
        WS_TS.WS_OUTPUT_FMT.WS_TX_TYP = BPRNHIST.TX_TYP;
        WS_TS.WS_OUTPUT_FMT.WS_TX_DT = BPRNHIST.TX_DT;
        WS_TS.WS_OUTPUT_FMT.WS_TX_TM = BPRNHIST.TX_TM;
        WS_TS.WS_OUTPUT_FMT.WS_AC = BPRNHIST.AC;
        WS_TS.WS_OUTPUT_FMT.WS_CI_NO = BPRNHIST.CI_NO;
        WS_TS.WS_OUTPUT_FMT.WS_TX_TOOL = BPRNHIST.TX_TOOL;
        WS_TS.WS_OUTPUT_FMT.WS_REF_NO = BPRNHIST.REF_NO;
        WS_TS.WS_OUTPUT_FMT.WS_APP_MMO = BPRNHIST.APP_MMO;
        WS_TS.WS_OUTPUT_FMT.WS_TX_CD = BPRNHIST.TX_CD;
        WS_TS.WS_OUTPUT_FMT.WS_TX_CHNL = BPRNHIST.TX_CHNL;
        CEP.TRC(SCCGWA, BPRNHIST.SUP1);
        WS_TS.WS_OUTPUT_FMT.WS_TX_TLR = BPRNHIST.TX_TLR;
        WS_TS.WS_OUTPUT_FMT.WS_SUP1 = BPRNHIST.SUP1;
        WS_TS.WS_OUTPUT_FMT.WS_SUP2 = BPRNHIST.SUP2;
        WS_TS.WS_OUTPUT_FMT.WS_TX_BR = BPRNHIST.TX_BR;
        WS_TS.WS_OUTPUT_FMT.WS_TX_DP = BPRNHIST.TX_DP;
        WS_TS.WS_OUTPUT_FMT.WS_TX_TYP_CD = BPRNHIST.TX_TYP_CD;
        WS_TS.WS_OUTPUT_FMT.WS_TX_RMK = BPRNHIST.TX_RMK;
        WS_TS.WS_OUTPUT_FMT.WS_MAKER_TLR = BPRNHIST.MAKER_TLR;
        WS_TS.WS_OUTPUT_FMT.WS_TX_CD_RMK = WS_TS_FIELD.WS_TYP_DESC;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_AC_DT);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_JRNNO);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_JRN_SEQ);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_TYP);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_DT);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_TM);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_AC);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_CI_NO);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_REF_NO);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_APP_MMO);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_CD);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_CHNL);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_TLR);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_SUP1);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_SUP2);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_MAKER_TLR);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_BR);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_DP);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_TYP_CD);
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT.WS_TX_RMK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_TS.WS_OUTPUT_FMT);
        SCCMPAG.DATA_LEN = 560;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_BPRNHIST_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_AC_DT_CN = BPRNHIST.KEY.AC_DT;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_AC_DT_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_JRNNO_CN = BPRNHIST.KEY.JRNNO;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_JRNNO_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_JRN_SEQ_CN = BPRNHIST.KEY.JRN_SEQ;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_JRN_SEQ_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TYP_CN = BPRNHIST.TX_TYP;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TYP_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_DT_CN = BPRNHIST.TX_DT;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_DT_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TM_CN = BPRNHIST.TX_TM;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TM_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_AC_CN = BPRNHIST.AC;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_AC_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_AC_SEQ_CN = BPRNHIST.AC_SEQ;
        WS_TS.WS_OUTPUT_FMT_CN.WS_CCY_CN = BPRNHIST.CCY;
        WS_TS.WS_OUTPUT_FMT_CN.WS_CCY_TYPE_CN = BPRNHIST.CCY_TYPE;
        WS_TS.WS_OUTPUT_FMT_CN.WS_CI_NO_CN = BPRNHIST.CI_NO;
        if (WS_TS.WS_OUTPUT_FMT_CN.WS_CI_NO_CN.trim().length() == 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPRNHIST.AC;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            if (CICACCU.RC.RC_CODE == 0) {
                WS_TS.WS_OUTPUT_FMT_CN.WS_CI_NO_CN = CICACCU.DATA.CI_NO;
            }
        }
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_CI_NO_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TOOL_CN = BPRNHIST.TX_TOOL;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TOOL_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_REF_NO_CN = BPRNHIST.REF_NO;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_REF_NO_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_APP_MMO_CN = BPRNHIST.APP_MMO;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_APP_MMO_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_CD_CN = BPRNHIST.TX_CD;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_CD_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_CHNL_CN = BPRNHIST.TX_CHNL;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_CHNL_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TLR_CN = BPRNHIST.TX_TLR;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TLR_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_SUP1_CN = BPRNHIST.SUP1;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_SUP1_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_SUP2_CN = BPRNHIST.SUP2;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_SUP2_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_BR_CN = BPRNHIST.TX_BR;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_BR_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_DP_CN = BPRNHIST.TX_DP;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_DP_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TYP_CD_CN = BPRNHIST.TX_TYP_CD;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_TYP_CD_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_MAKER_TLR_CN = BPRNHIST.MAKER_TLR;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_MAKER_TLR_CN);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_RMK_CN = BPRNHIST.TX_RMK;
        WS_TS.WS_OUTPUT_FMT_CN.WS_OLD_DAT_CN = BPRNHIST.BLOB_OLD_DATA;
        WS_TS.WS_OUTPUT_FMT_CN.WS_NEW_DAT_CN = BPRNHIST.BLOB_NEW_DATA;
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_REV_DT = BPRNHIST.TX_REV_DT;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_REV_DT);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_STS = BPRNHIST.TX_STS;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_STS);
        WS_TS.WS_OUTPUT_FMT_CN.WS_TX_VAL_DT = BPRNHIST.TX_VAL_DT;
        CEP.TRC(SCCGWA, WS_TS.WS_OUTPUT_FMT_CN.WS_TX_VAL_DT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_TS.WS_OUTPUT_FMT_CN;
        SCCFMT.DATA_LEN = 2884;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_PAGE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_TS.WS_TS_QUEUE);
        SCCMPAG.DATA_LEN = 643;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTHISD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PROC_NHISD, BPCTHISD);
        CEP.TRC(SCCGWA, BPCTHISD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHISD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHISD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PROC_NHIST, BPCTHIST);
        CEP.TRC(SCCGWA, BPCTHIST.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUINHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUINHD = ");
            CEP.TRC(SCCGWA, BPCUINHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
