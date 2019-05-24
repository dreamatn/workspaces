package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZMTSPB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_CNT = 99;
    int K_ZERO = 0;
    char K_NORMAL = 'N';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    short WS_I = 0;
    short WS_II = 0;
    LNZMTSPB_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZMTSPB_WS_TEMP_VARIABLE();
    LNZMTSPB_WS_MSG_INFO WS_MSG_INFO = new LNZMTSPB_WS_MSG_INFO();
    long WS_SEQ_NO = 0;
    int WS_SUB_CTA_NO = 0;
    double WS_SCHT_AMOUNT = 0;
    double WS_PLPI_AMT = 0;
    short WS_PLPI_TERM = 0;
    char WS_REPY_MTH = ' ';
    String WS_PLPI_REMARK = "                                                                                                                                                                                                                                                ";
    double WS_TOT_P_AMT = 0;
    int WS_DUE_DT = 0;
    char WS_EXIST_SCHE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNCIPART LNCIPART = new LNCIPART();
    LNCIPARA LNCIPARA = new LNCIPARA();
    LNRDISB LNRDISB = new LNRDISB();
    LNCRDISB LNCRDISB = new LNCRDISB();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNRSCHT LNRSCH2 = new LNRSCHT();
    LNRPLPI LNRPLP2 = new LNRPLPI();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCMTSPB LNCMTSPB;
    public void MP(SCCGWA SCCGWA, LNCMTSPB LNCMTSPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCMTSPB = LNCMTSPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZMTSPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCMTSPB.COMM_DATA.CTA_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMTSPB.COMM_DATA.FUNC_CD != '1' 
            && LNCMTSPB.COMM_DATA.FUNC_CD != '2' 
            && LNCMTSPB.COMM_DATA.FUNC_CD != '3' 
            && LNCMTSPB.COMM_DATA.FUNC_CD != '4') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCMTSPB.COMM_DATA.ACTION != 'A' 
            && LNCMTSPB.COMM_DATA.ACTION != 'M' 
            && LNCMTSPB.COMM_DATA.ACTION != 'D') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.FUNC_CD);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.ACTION);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.TYPE);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.TERM_NO);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, "****************************");
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        R000_GET_PARTICIPANT_INFO();
        if (pgmRtn) return;
        if (LNCIPART.DATA.CNT > 0) {
            B200_MAIN_STEP();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "WWWW---WITHOUT SUB BANK");
        }
    }
    public void B200_MAIN_STEP() throws IOException,SQLException,Exception {
        if (LNCMTSPB.COMM_DATA.FUNC_CD == '1'
            && LNCMTSPB.COMM_DATA.ACTION == 'A') {
            B310_GET_TOP_SCHT_INFO();
            if (pgmRtn) return;
            B320_ADD_PART_LNTSCHT();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '1'
            && LNCMTSPB.COMM_DATA.ACTION == 'M') {
            B310_GET_TOP_SCHT_INFO();
            if (pgmRtn) return;
            B330_UPD_PART_LNTSCHT();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '1'
            && LNCMTSPB.COMM_DATA.ACTION == 'D') {
            B350_DEL_PART_LNTSCHT();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '2'
            && LNCMTSPB.COMM_DATA.ACTION == 'A'
            || LNCMTSPB.COMM_DATA.FUNC_CD == '3'
            && LNCMTSPB.COMM_DATA.ACTION == 'A') {
            B410_GET_TOP_PLPI_INFO();
            if (pgmRtn) return;
            B420_ADD_PART_LNTPLPI();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '2'
            && LNCMTSPB.COMM_DATA.ACTION == 'M') {
            B410_GET_TOP_PLPI_INFO();
            if (pgmRtn) return;
            B430_UPD_PART_LNTPLPI();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '2'
            && LNCMTSPB.COMM_DATA.ACTION == 'D') {
            B450_DEL_PART_LNTPLPI();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '4'
            && LNCMTSPB.COMM_DATA.ACTION == 'A') {
            B410_GET_TOP_PLPI_INFO();
            if (pgmRtn) return;
            B460_ADD_PART_LNTPLPI();
            if (pgmRtn) return;
        } else if (LNCMTSPB.COMM_DATA.FUNC_CD == '4'
            && LNCMTSPB.COMM_DATA.ACTION == 'D'
            || LNCMTSPB.COMM_DATA.FUNC_CD == '3'
            && LNCMTSPB.COMM_DATA.ACTION == 'D') {
            B470_DEL_PART_LNTPLPI();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R000_GET_PARTICIPANT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.RC);
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNCIPART.DATA.REL_TYPE_IN = 'P';
        CEP.TRC(SCCGWA, LNCIPART.DATA.CONTRACT_NO);
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
    }
    public void B310_GET_TOP_SCHT_INFO() throws IOException,SQLException,Exception {
        WS_SUB_CTA_NO = K_ZERO;
        R000_READ_LNTSCHT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, LNRSCHT, LNRSCH2);
        WS_SCHT_AMOUNT = LNRSCHT.AMOUNT;
    }
    public void B320_ADD_PART_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPARA.RC);
        IBS.init(SCCGWA, LNCIPARA.DATA);
        LNCIPARA.DATA.FUNC = 'R';
        LNCIPARA.DATA.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNCIPARA.DATA.TOT_AMT = WS_SCHT_AMOUNT;
        CEP.TRC(SCCGWA, WS_SCHT_AMOUNT);
        S000_CALL_LNZIPARA();
        if (pgmRtn) return;
        for (WS_II = 1; WS_II <= LNCIPARA.DATA.CNT 
            && LNCIPARA.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            CEP.TRC(SCCGWA, LNCIPARA.GROUP.get(WS_II-1).SEQ_NO);
            CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
            WS_SUB_CTA_NO = LNCIPARA.GROUP.get(WS_II-1).SEQ_NO;
            LNRSCHT.AMOUNT = LNCIPARA.GROUP.get(WS_II-1).DISB_AMT;
            R000_ADD_LNTSCHT();
            if (pgmRtn) return;
        }
    }
    public void B330_UPD_PART_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPARA.RC);
        IBS.init(SCCGWA, LNCIPARA.DATA);
        LNCIPARA.DATA.FUNC = 'R';
        LNCIPARA.DATA.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNCIPARA.DATA.TOT_AMT = LNRSCH2.AMOUNT;
        CEP.TRC(SCCGWA, LNCIPARA.DATA.TOT_AMT);
        S000_CALL_LNZIPARA();
        if (pgmRtn) return;
        for (WS_II = 1; WS_II <= LNCIPARA.DATA.CNT 
            && LNCIPARA.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPARA.GROUP.get(WS_II-1).SEQ_NO;
            CEP.TRC(SCCGWA, LNCIPARA.GROUP.get(WS_II-1).SEQ_NO);
            R000_READUP_LNTSCHT();
            if (pgmRtn) return;
            LNRSCHT.AMOUNT = LNCIPARA.GROUP.get(WS_II-1).DISB_AMT;
            LNRSCHT.ALL_IN_RATE = LNRSCH2.ALL_IN_RATE;
            LNRSCHT.ACTION = LNRSCH2.ACTION;
            LNRSCHT.VAL_DTE = LNRSCH2.VAL_DTE;
            LNRSCHT.DUE_DTE = LNRSCH2.DUE_DTE;
            LNRSCHT.REMARK = LNRSCH2.REMARK;
            R000_UPDATE_LNTSCHT();
            if (pgmRtn) return;
        }
    }
    public void B350_DEL_PART_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPARA.RC);
        IBS.init(SCCGWA, LNCIPARA.DATA);
        LNCIPARA.DATA.FUNC = 'R';
        LNCIPARA.DATA.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNCIPARA.DATA.TOT_AMT = LNRSCH2.AMOUNT;
        CEP.TRC(SCCGWA, LNCIPARA.DATA.TOT_AMT);
        S000_CALL_LNZIPARA();
        if (pgmRtn) return;
        for (WS_II = 1; WS_II <= LNCIPARA.DATA.CNT 
            && LNCIPARA.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPARA.GROUP.get(WS_II-1).SEQ_NO;
            R000_READUP_LNTSCHT();
            if (pgmRtn) return;
            R000_DELETE_LNTSCHT();
            if (pgmRtn) return;
        }
    }
    public void B410_GET_TOP_PLPI_INFO() throws IOException,SQLException,Exception {
        WS_SUB_CTA_NO = K_ZERO;
        R000_READ_LNTPLPI();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, LNRPLPI, LNRPLP2);
    }
    public void B420_ADD_PART_LNTPLPI() throws IOException,SQLException,Exception {
        for (WS_II = 1; WS_II <= LNCIPART.DATA.CNT 
            && LNCIPART.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPART.GROUP.get(WS_II-1).SEQ_NO;
            WS_DUE_DT = LNRPLPI.DUE_DT;
            CEP.TRC(SCCGWA, WS_DUE_DT);
            CEP.TRC(SCCGWA, WS_SUB_CTA_NO);
            R000_READ_LNTSCHT_BY_DUE_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
            CEP.TRC(SCCGWA, LNRSCHT.AMOUNT);
            if (LNCMTSPB.COMM_DATA.TYPE == 'P') {
                WS_PLPI_AMT = LNRSCHT.AMOUNT;
            } else {
                WS_PLPI_AMT = 0;
            }
            WS_PLPI_REMARK = LNRSCHT.REMARK;
            R000_ADD_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B430_UPD_PART_LNTPLPI() throws IOException,SQLException,Exception {
        for (WS_II = 1; WS_II <= LNCIPART.DATA.CNT 
            && LNCIPART.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPART.GROUP.get(WS_II-1).SEQ_NO;
            WS_DUE_DT = LNRPLPI.DUE_DT;
            CEP.TRC(SCCGWA, WS_DUE_DT);
            CEP.TRC(SCCGWA, WS_SUB_CTA_NO);
            R000_READ_LNTSCHT_BY_DUE_DT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
            if (LNRSCHT.KEY.SUB_CTA_NO.trim().length() == 0) WS_SUB_CTA_NO = 0;
            else WS_SUB_CTA_NO = Integer.parseInt(LNRSCHT.KEY.SUB_CTA_NO);
            WS_REPY_MTH = LNRSCHT.KEY.TYPE;
            WS_PLPI_TERM = LNRSCHT.KEY.TERM;
            R000_READUP_LNTPLPI();
            if (pgmRtn) return;
            if (LNRSCHT.KEY.SUB_CTA_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
            else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNRSCHT.KEY.SUB_CTA_NO);
            LNRPLPI.VAL_DT = LNRSCHT.VAL_DTE;
            LNRPLPI.DUE_DT = LNRSCHT.DUE_DTE;
            LNRPLPI.DUE_REPY_AMT = LNRSCHT.AMOUNT;
            LNRPLPI.PRIN_AMT = LNRSCHT.AMOUNT;
            LNRPLPI.REMARK = LNRSCHT.REMARK;
            R000_UPDATE_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B450_DEL_PART_LNTPLPI() throws IOException,SQLException,Exception {
        for (WS_II = 1; WS_II <= LNCIPART.DATA.CNT 
            && LNCIPART.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPART.GROUP.get(WS_II-1).SEQ_NO;
            CEP.TRC(SCCGWA, WS_SUB_CTA_NO);
            R000_READ_LNTSCHT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
            if (LNRSCHT.KEY.SUB_CTA_NO.trim().length() == 0) WS_SUB_CTA_NO = 0;
            else WS_SUB_CTA_NO = Integer.parseInt(LNRSCHT.KEY.SUB_CTA_NO);
            WS_REPY_MTH = LNRSCHT.KEY.TYPE;
            WS_PLPI_TERM = LNRSCHT.KEY.TERM;
            R000_READUP_LNTPLPI();
            if (pgmRtn) return;
            if (LNRSCHT.KEY.SUB_CTA_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
            else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNRSCHT.KEY.SUB_CTA_NO);
            LNRPLPI.VAL_DT = LNRSCHT.VAL_DTE;
            LNRPLPI.DUE_DT = LNRSCHT.DUE_DTE;
            LNRPLPI.DUE_REPY_AMT = LNRSCHT.AMOUNT;
            LNRPLPI.PRIN_AMT = LNRSCHT.AMOUNT;
            LNRPLPI.REMARK = LNRSCHT.REMARK;
            R000_DELETE_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B460_ADD_PART_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPARA.RC);
        IBS.init(SCCGWA, LNCIPARA.DATA);
        LNCIPARA.DATA.FUNC = 'R';
        LNCIPARA.DATA.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNCIPARA.DATA.TOT_AMT = LNCMTSPB.COMM_DATA.AMOUNT;
        CEP.TRC(SCCGWA, LNCMTSPB.COMM_DATA.AMOUNT);
        S000_CALL_LNZIPARA();
        if (pgmRtn) return;
        for (WS_II = 1; WS_II <= LNCIPARA.DATA.CNT 
            && LNCIPARA.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            CEP.TRC(SCCGWA, LNCIPARA.GROUP.get(WS_II-1).SEQ_NO);
            WS_SUB_CTA_NO = LNCIPARA.GROUP.get(WS_II-1).SEQ_NO;
            WS_PLPI_AMT = LNCIPARA.GROUP.get(WS_II-1).DISB_AMT;
            WS_PLPI_REMARK = " ";
            R000_ADD_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void B470_DEL_PART_LNTPLPI() throws IOException,SQLException,Exception {
        for (WS_II = 1; WS_II <= LNCIPART.DATA.CNT 
            && LNCIPART.GROUP.get(WS_II-1).SEQ_NO != 0 
            && WS_II <= K_MAX_CNT; WS_II += 1) {
            WS_SUB_CTA_NO = LNCIPART.GROUP.get(WS_II-1).SEQ_NO;
            CEP.TRC(SCCGWA, WS_SUB_CTA_NO);
            WS_REPY_MTH = LNCMTSPB.COMM_DATA.TYPE;
            WS_PLPI_TERM = LNCMTSPB.COMM_DATA.TERM_NO;
            R000_READUP_LNTPLPI();
            if (pgmRtn) return;
            R000_DELETE_LNTPLPI();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSPB.COMM_DATA.TRAN_SEQ;
        if (LNCMTSPB.COMM_DATA.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCMTSPB.COMM_DATA.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = LNCMTSPB.COMM_DATA.TYPE;
        LNRSCHT.KEY.TERM = LNCMTSPB.COMM_DATA.TERM_NO;
        LNCRSCHT.FUNC = 'I';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_READ_LNTSCHT_BY_DUE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSPB.COMM_DATA.TRAN_SEQ;
        if (LNCMTSPB.COMM_DATA.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCMTSPB.COMM_DATA.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = LNCMTSPB.COMM_DATA.TYPE;
        LNRSCHT.DUE_DTE = WS_DUE_DT;
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'Y';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_READUP_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSCHT);
        IBS.init(SCCGWA, LNCRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSPB.COMM_DATA.TRAN_SEQ;
        if (LNCMTSPB.COMM_DATA.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCMTSPB.COMM_DATA.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = LNCMTSPB.COMM_DATA.TYPE;
        LNRSCHT.KEY.TERM = LNCMTSPB.COMM_DATA.TERM_NO;
        CEP.TRC(SCCGWA, LNRSCHT.KEY);
        LNCRSCHT.FUNC = 'R';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'U';
        LNRSCHT.KEY.SUB_CTA_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_ADD_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.SUB_CTA_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_DELETE_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'D';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_READ_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = K_ZERO;
        LNRPLPI.KEY.REPY_MTH = LNCMTSPB.COMM_DATA.TYPE;
        LNRPLPI.KEY.TERM = LNCMTSPB.COMM_DATA.TERM_NO;
        LNCRPLPI.FUNC = 'I';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_READUP_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'R';
        LNRPLPI.KEY.CONTRACT_NO = LNCMTSPB.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = WS_REPY_MTH;
        LNRPLPI.KEY.TERM = WS_PLPI_TERM;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_UPDATE_LNTPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'U';
        LNRPLPI.REMARK = LNRSCHT.REMARK;
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_ADD_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        IBS.CLONE(SCCGWA, LNRPLP2, LNRPLPI);
        LNCRPLPI.FUNC = 'A';
        LNRPLPI.KEY.SUB_CTA_NO = WS_SUB_CTA_NO;
        LNRPLPI.DUE_REPY_AMT = WS_PLPI_AMT;
        LNRPLPI.PRIN_AMT = WS_PLPI_AMT;
        LNRPLPI.REC_STS = K_NORMAL;
        LNRPLPI.REMARK = WS_PLPI_REMARK;
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        LNCRPLPI.FUNC = 'D';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNRSCHT.KEY.TRAN_SEQ = LNCMTSPB.COMM_DATA.TRAN_SEQ;
        if (LNCMTSPB.COMM_DATA.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNCMTSPB.COMM_DATA.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + K_ZERO;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TYPE = LNCMTSPB.COMM_DATA.TYPE;
        LNRSCHT.KEY.TERM = LNCMTSPB.COMM_DATA.TERM_NO;
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'T';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'R';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_LNTSCHT() throws IOException,SQLException,Exception {
        LNCRSCHT.FUNC = 'B';
        LNCRSCHT.OPT = 'E';
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        CEP.TRC(SCCGWA, LNCRSCHT.RC);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        CEP.TRC(SCCGWA, LNCRPLPI.RC);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPARA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PARTI-AMT-DISB", LNCIPARA);
        if (LNCIPARA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPARA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
        if (LNCMTSPB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCMTSPB=");
            CEP.TRC(SCCGWA, LNCMTSPB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
