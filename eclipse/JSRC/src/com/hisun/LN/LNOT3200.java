package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3200 {
    DBParm LNTCONT_RD;
    char K_PRIN = 'P';
    char K_INT = 'I';
    short K_CNT = 20;
    char WS_REPY_TYP = ' ';
    short WS_INPUT_TERM = 0;
    String WS_ERR_MSG = " ";
    LNOT3200_WS_MSG_INFO WS_MSG_INFO = new LNOT3200_WS_MSG_INFO();
    LNOT3200_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT3200_WS_TEMP_VARIABLE();
    char WS_P_FLG = ' ';
    char WS_I_FLG = ' ';
    char WS_PLPI_END_FLG = ' ';
    LNOT3200_WS_OUTPUT WS_OUTPUT = new LNOT3200_WS_OUTPUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    LNCMTSCH LNCMTSCH = new LNCMTSCH();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNB3100_AWA_3100 LNB3100_AWA_3100;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT3200 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3100_AWA_3100>");
        LNB3100_AWA_3100 = (LNB3100_AWA_3100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_FIRST();
        for (WS_TEMP_VARIABLE.WS_IDX = 1; (WS_TEMP_VARIABLE.WS_IDX <= K_CNT); WS_TEMP_VARIABLE.WS_IDX += 1) {
            if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP > SPACE 
                || LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT != 0 
                || LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RATE != 0 
                || LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_AMT != 0 
                || LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RMK.trim().length() > 0) {
                WS_TEMP_VARIABLE.WS_TOT_RECORD += 1;
                B100_CHECK_INPUT();
                B200_CALL_SVR_PROCESS();
            }
        }
        if (LNB3100_AWA_3100.CONF_FLG == 'Y') {
            B300_CALL_SVR_PROCESS();
            B3000_OUTPUT_PROCESS();
        }
    }
    public void B100_CHECK_INPUT_FIRST() throws IOException,SQLException,Exception {
        if (LNB3100_AWA_3100.CTA_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB3100_AWA_3100.CTA_NO;
        T000_READ_LNTCONT();
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNB3100_AWA_3100.CTA_NO;
        LNCAPRDM.FUNC = '3';
        S000_CALL_LNZAPRDM();
        if (LNCAPRDM.REC_DATA.PAY_MTH != '5') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_1684;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.CONF_FLG == 'Y') {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '3';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB3100_AWA_3100.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            B310_DELETE_LNTPLPI();
        }
    }
    public void B310_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        WS_REPY_TYP = 'I';
        WS_INPUT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        WS_REPY_TYP = 'P';
        WS_INPUT_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        WS_REPY_TYP = 'C';
        WS_INPUT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
    }
    public void R000_DEL_PLPI_UNTIL_NF() throws IOException,SQLException,Exception {
        WS_PLPI_END_FLG = 'N';
        S000_CALL_LNZRPLPI_DELETE();
        while (WS_PLPI_END_FLG != 'Y') {
            S000_CALL_LNZRPLPI_DELETE();
        }
    }
    public void S000_CALL_LNZRPLPI_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'R';
        LNRPLPI.KEY.CONTRACT_NO = LNB3100_AWA_3100.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_REPY_TYP;
        LNRPLPI.KEY.TERM = WS_INPUT_TERM;
        CEP.TRC(SCCGWA, LNB3100_AWA_3100.CTA_NO);
        CEP.TRC(SCCGWA, WS_REPY_TYP);
        CEP.TRC(SCCGWA, WS_INPUT_TERM);
        S000_CALL_LNZRPLPI();
        CEP.TRC(SCCGWA, LNCRPLPI.RC.RC_CODE);
        if (LNCRPLPI.RC.RC_CODE == 0) {
            LNCRPLPI.FUNC = 'D';
            S000_CALL_LNZRPLPI();
        }
        if (LNCRPLPI.RC.RC_CODE == 0) {
            WS_INPUT_TERM += 1;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP <= SPACE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT_PAY_TYP;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_DATE;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUE_DATE;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP == K_PRIN) {
            if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT == LNRCONT.MAT_DATE) {
                WS_P_FLG = 'Y';
            } else {
                if (WS_P_FLG != 'Y') {
                    WS_P_FLG = 'N';
                }
            }
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP == K_INT) {
            if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT == LNRCONT.MAT_DATE) {
                WS_I_FLG = 'Y';
            } else {
                if (WS_I_FLG != 'Y') {
                    WS_I_FLG = 'N';
                }
            }
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP != K_PRIN 
            && LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP != K_INT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP == K_PRIN 
            && LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_AMT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_P_AMT_ZERO;
            S000_ERR_MSG_PROC();
        }
        if (LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP == K_INT 
            && LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_AMT != 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_I_AMT_NOT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMTSCH);
        LNCMTSCH.COMM_DATA.FUNC = LNB3100_AWA_3100.FUNC;
        LNCMTSCH.COMM_DATA.CTA_NO = LNB3100_AWA_3100.CTA_NO;
        LNCMTSCH.COMM_DATA.TRAN_SEQ = LNB3100_AWA_3100.TRAN_SEQ;
        LNCMTSCH.COMM_DATA.LIST_TYP = LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RPTP;
        LNCMTSCH.COMM_DATA.DUE_DT = LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_VLDT;
        LNCMTSCH.COMM_DATA.RATE = LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RATE;
        LNCMTSCH.COMM_DATA.AMOUNT = LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_AMT;
        LNCMTSCH.COMM_DATA.REMARK = LNB3100_AWA_3100.MRS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].MRS_RMK;
        S000_CALL_LNZMTSCH();
    }
    public void B300_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCMSCH);
        LNCCMSCH.COMM_DATA.SEQ_NO = LNB3100_AWA_3100.TRAN_SEQ;
        LNCCMSCH.COMM_DATA.CTA_NO = LNB3100_AWA_3100.CTA_NO;
        S000_CALL_SVR_LNZCMSCH();
        WS_OUTPUT.WS_CONTRACT_NO = LNCCMSCH.O_DATA.O_CONTRACT_NO;
        WS_OUTPUT.WS_TRAN_SEQ = LNCCMSCH.O_DATA.O_TRAN_SEQ;
        WS_OUTPUT.WS_PAY_TYPE = LNCCMSCH.O_DATA.O_PAY_TYPE;
        for (WS_TEMP_VARIABLE.WS_IDX = 1; (WS_TEMP_VARIABLE.WS_IDX <= K_CNT); WS_TEMP_VARIABLE.WS_IDX += 1) {
            WS_OUTPUT.WS_LIST[WS_TEMP_VARIABLE.WS_IDX-1].WS_LIST_DATA.WS_L_TYP = LNCCMSCH.O_DATA.O_LIST[WS_TEMP_VARIABLE.WS_IDX-1].LIST_DATA.L_TYP;
            WS_OUTPUT.WS_LIST[WS_TEMP_VARIABLE.WS_IDX-1].WS_LIST_DATA.WS_L_DT = LNCCMSCH.O_DATA.O_LIST[WS_TEMP_VARIABLE.WS_IDX-1].LIST_DATA.L_DT;
            WS_OUTPUT.WS_LIST[WS_TEMP_VARIABLE.WS_IDX-1].WS_LIST_DATA.WS_L_RATE = LNCCMSCH.O_DATA.O_LIST[WS_TEMP_VARIABLE.WS_IDX-1].LIST_DATA.L_RATE;
            WS_OUTPUT.WS_LIST[WS_TEMP_VARIABLE.WS_IDX-1].WS_LIST_DATA.WS_L_AMT = LNCCMSCH.O_DATA.O_LIST[WS_TEMP_VARIABLE.WS_IDX-1].LIST_DATA.L_AMT;
            WS_OUTPUT.WS_LIST[WS_TEMP_VARIABLE.WS_IDX-1].WS_LIST_DATA.WS_L_RMK = LNCCMSCH.O_DATA.O_LIST[WS_TEMP_VARIABLE.WS_IDX-1].LIST_DATA.L_RMK;
        }
    }
    public void B3000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN320";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 3205;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZMTSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MAINTIAN-SCHT", LNCMTSCH);
        if (LNCMTSCH.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCMTSCH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SVR_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH, true);
        if (LNCCMSCH.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCMSCH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            if (LNCRPLPI.RETURN_INFO == 'N') {
                WS_PLPI_END_FLG = 'Y';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.CONT_NFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
