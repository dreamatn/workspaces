package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZQRSCH {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_RATE_TYP_NORMAL = 'N';
    char K_INQUIRY = 'I';
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNZQRSCH_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZQRSCH_WS_TEMP_VARIABLE();
    LNZQRSCH_WS_MSG_INFO WS_MSG_INFO = new LNZQRSCH_WS_MSG_INFO();
    short WS_I = 0;
    String WS_TRANCHE_NO = " ";
    int WS_SUB_CTA_NO = 0;
    int WS_RATQ_VAL_DT = 0;
    int WS_LAST_WK_DATE = 0;
    String WS_CNTY_CD_INF = " ";
    LNZQRSCH_REDEFINES16 REDEFINES16 = new LNZQRSCH_REDEFINES16();
    int WS_RCVD_DATE = 0;
    char WS_INQ_KEY_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCRATQ LNCRATQ = new LNCRATQ();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    LNRSCHT LNRSCHT = new LNRSCHT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRSCHT LNCRSCHT = new LNCRSCHT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCQRSCH LNCQRSCH;
    public void MP(SCCGWA SCCGWA, LNCQRSCH LNCQRSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCQRSCH = LNCQRSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZQRSCH return!");
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
        B200_MAIN_PROCESS_LOGIC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((LNCQRSCH.COMM_DATA.BANK_NAM.compareTo(SPACE) > 0) 
            && (LNCQRSCH.COMM_DATA.BK_CNTY.compareTo(SPACE) > 0)) {
            WS_INQ_KEY_FLAG = 'B';
        } else {
            WS_INQ_KEY_FLAG = 'L';
        }
    }
    public void B200_MAIN_PROCESS_LOGIC() throws IOException,SQLException,Exception {
        B211_GET_TRANCHE_COMMIT();
        if (pgmRtn) return;
        B213_GET_LAST_WORKING_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LAST_WK_DATE);
        R000_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        R000_READNEXT_LNTRCVD();
        if (pgmRtn) return;
        while (LNCRRCVD.RETURN_INFO != 'E') {
            if (LNRRCVD.DUE_DT > WS_RCVD_DATE) {
                WS_RCVD_DATE = LNRRCVD.DUE_DT;
            }
            R000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
        }
        R000_ENDBR_LNTRCVD();
        if (pgmRtn) return;
        if (WS_RCVD_DATE > WS_LAST_WK_DATE) {
            WS_LAST_WK_DATE = WS_RCVD_DATE;
        }
        CEP.TRC(SCCGWA, WS_LAST_WK_DATE);
        B215_01_STARTBR_PLPI();
        if (pgmRtn) return;
        B215_02_READNEXT_PLPI();
        if (pgmRtn) return;
        while (LNCRPLPI.RETURN_INFO != 'E') {
            if (LNRPLPI.DUE_DT > WS_LAST_WK_DATE) {
                CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
                B217_WRITE_LNTSCHT();
                if (pgmRtn) return;
            }
            B215_02_READNEXT_PLPI();
            if (pgmRtn) return;
        }
        B215_03_ENDBR_PLPI();
        if (pgmRtn) return;
    }
    public void B211_GET_TRANCHE_COMMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCQRSCH.COMM_DATA.CTA_NO;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_SRC_LNZRCONT();
        if (pgmRtn) return;
        WS_TRANCHE_NO = LNRCONT.FATHER_CONTRACT;
        CEP.TRC(SCCGWA, WS_TRANCHE_NO);
    }
    public void B213_GET_LAST_WORKING_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCQRSCH.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
        if (LNCRICTL.RETURN_INFO == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5702;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_LAST_WK_DATE = LNRICTL.INT_CUT_DT;
    }
    public void B215_01_STARTBR_PLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'A';
        LNRPLPI.KEY.CONTRACT_NO = LNCQRSCH.COMM_DATA.CTA_NO;
        if (LNCQRSCH.COMM_DATA.SUF_NO.trim().length() == 0) LNRPLPI.KEY.SUB_CTA_NO = 0;
        else LNRPLPI.KEY.SUB_CTA_NO = Integer.parseInt(LNCQRSCH.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCQRSCH.COMM_DATA.SUF_NO);
        LNRPLPI.KEY.REPY_MTH = LNCQRSCH.COMM_DATA.REPY_TYP;
        LNRPLPI.KEY.TERM = 0;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B215_02_READNEXT_PLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'R';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B215_03_ENDBR_PLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'B';
        LNCRPLPI.OPT = 'E';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B216_GET_ALL_IN_RATE() throws IOException,SQLException,Exception {
        WS_RATQ_VAL_DT = LNRPLPI.DUE_DT;
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNCQRSCH.COMM_DATA.CTA_NO;
        LNCRATQ.COMM_DATA.SUF_NO = LNCQRSCH.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = K_RATE_TYP_NORMAL;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_RATQ_VAL_DT;
        SCCCLDT.DAYS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.VAL_DATE = SCCCLDT.DATE2;
        S000_CALL_FUNC_LNZRATQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.DUE_DT);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.RATE);
    }
    public void B217_WRITE_LNTSCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSCHT);
        IBS.init(SCCGWA, LNRSCHT);
        LNCRSCHT.FUNC = 'A';
        LNRSCHT.KEY.TRAN_SEQ = LNCQRSCH.COMM_DATA.SEQ_NO;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        LNRSCHT.KEY.CONTRACT_NO = 0;
        LNRSCHT.KEY.SUB_CTA_NO = LNCQRSCH.COMM_DATA.CTA_NO;
        LNRSCHT.KEY.TYPE = LNRPLPI.KEY.REPY_MTH;
        LNRSCHT.KEY.TERM = LNRPLPI.KEY.TERM;
        LNRSCHT.ACTION = K_INQUIRY;
        LNRSCHT.VAL_DTE = LNRPLPI.VAL_DT;
        LNRSCHT.DUE_DTE = LNRPLPI.DUE_DT;
        LNRSCHT.AMOUNT = LNRPLPI.DUE_REPY_AMT;
        LNRSCHT.REMARK = LNRPLPI.REMARK;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNRSCHT.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        LNCRSCHT.REC_PTR = LNRSCHT;
        LNCRSCHT.REC_LEN = 356;
        S000_CALL_SRC_LNZRSCHT();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRRCVD);
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = '4';
        LNRRCVD.KEY.CONTRACT_NO = LNCQRSCH.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.TERM = 1;
        LNCRRCVD.REC_PTR = LNRRCVD;
        LNCRRCVD.REC_LEN = 477;
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'R';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNCRRCVD.FUNC = 'B';
        LNCRRCVD.OPT = 'E';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRRCVD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRCVD", LNCRRCVD);
        if (LNCRRCVD.RC.RC_CODE != 0) {
            if (LNCRRCVD.RETURN_INFO != 'E') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            if (LNCRCONT.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCMMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        if (LNCRCMMT.RC.RC_CODE != 0) {
            if (LNCRCMMT.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        CEP.TRC(SCCGWA, LNCRPLPI.RC);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCQRSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRSCHT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSCHT", LNCRSCHT);
        if (LNCRSCHT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSCHT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCQRSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FUNC_LNZRATQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATH-INQ", LNCRATQ);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = "SC";
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        CEP.TRC(SCCGWA, LNCRICTL.RC.RC_CODE);
        if (LNCRICTL.RC.RC_CODE != 0) {
            if (LNCRICTL.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCQRSCH.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCQRSCH=");
            CEP.TRC(SCCGWA, LNCQRSCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
