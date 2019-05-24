package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class LNZADSCH {
    boolean pgmRtn = false;
    char K_NORMAL = 'N';
    char K_PRINCIPAL = 'P';
    char K_INTEREST = 'I';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNZADSCH_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZADSCH_WS_TEMP_VARIABLE();
    LNZADSCH_WS_MSG_INFO WS_MSG_INFO = new LNZADSCH_WS_MSG_INFO();
    double WS_P_OS_AMT = 0;
    String WS_TRANCHE_NO = " ";
    int WS_SUB_CTA_NO = 0;
    int WS_RATQ_VAL_DT = 0;
    short WS_LAST_TERM_NO = 0;
    short WS_NEXT_TERM_NO = 0;
    char WS_DATA_OK_FLAGE = ' ';
    LNRCONT LNRCONT = new LNRCONT();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRPLPI LNRPLP2 = new LNRPLPI();
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNCSPRIN LNCSPRIN = new LNCSPRIN();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRPLPI LNCRPLP2 = new LNCRPLPI();
    LNCRICTL LNCRICTL = new LNCRICTL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCADSCH LNCADSCH;
    public void MP(SCCGWA SCCGWA, LNCADSCH LNCADSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCADSCH = LNCADSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZADSCH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNCADSCH.COMM_DATA.FUNC_CD != 'A' 
            && LNCADSCH.COMM_DATA.FUNC_CD != 'D') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCADSCH.COMM_DATA.CONTRACT_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCADSCH.COMM_DATA.TERM <= 0) {
            if (LNCADSCH.COMM_DATA.VAL_DT <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_VALUE_DATE_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCADSCH.COMM_DATA.DUE_DT <= 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUE_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCADSCH.COMM_DATA.TYPE != K_PRINCIPAL 
            && LNCADSCH.COMM_DATA.TYPE != K_INTEREST) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCADSCH.COMM_DATA.TYPE <= SPACE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5716;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((LNCADSCH.COMM_DATA.TYPE == K_PRINCIPAL) 
            && LNCADSCH.COMM_DATA.FUNC_CD == 'A' 
            && (LNCADSCH.COMM_DATA.AMOUNT == 0) 
            && LNCADSCH.COMM_DATA.SUB_CTA_NO != 99990000) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5727;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[6] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[8] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((LNCADSCH.COMM_DATA.DUE_DT <= LNCADSCH.COMM_DATA.VAL_DT) 
            && (LNCADSCH.COMM_DATA.VAL_DT > 0) 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0132200") 
            && !JIBS_tmp_str[2].equalsIgnoreCase("0132941") 
            && !JIBS_tmp_str[4].equalsIgnoreCase("0132950") 
            && !JIBS_tmp_str[6].equalsIgnoreCase("0139121") 
            && !JIBS_tmp_str[8].equalsIgnoreCase("0662171") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VALDT_GRT_DUEDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B210_VALIDATE_DATA();
        if (pgmRtn) return;
        if (LNCADSCH.COMM_DATA.FUNC_CD == 'A') {
            B220_WRITE_LNTPLPI();
            if (pgmRtn) return;
        } else if (LNCADSCH.COMM_DATA.FUNC_CD == 'D') {
            B230_READUP_LNTPLPI();
            if (pgmRtn) return;
            B240_DELETE_LNTPLPI();
            if (pgmRtn) return;
        } else {
        }
        B260_SYN_UPD_LNTICTL();
        if (pgmRtn) return;
    }
    public void B210_VALIDATE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        B211_GET_MAX_TERM_NO();
        if (pgmRtn) return;
        if (LNCADSCH.COMM_DATA.FUNC_CD == 'A' 
            && LNCADSCH.COMM_DATA.TYPE == K_PRINCIPAL) {
            B212_GET_UN_BUILD_AMT();
            if (pgmRtn) return;
            WS_P_OS_AMT = LNRCONT.LN_TOT_AMT - LNCSPRIN.COMM_DATA.P_TOT_IPLN_AMT;
            CEP.TRC(SCCGWA, "ABC");
            CEP.TRC(SCCGWA, LNRCONT.LN_TOT_AMT);
            CEP.TRC(SCCGWA, LNCSPRIN.COMM_DATA.P_TOT_IPLN_AMT);
            CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.AMOUNT);
            CEP.TRC(SCCGWA, WS_P_OS_AMT);
            if (WS_P_OS_AMT < LNCADSCH.COMM_DATA.AMOUNT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5712;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "XXX");
                if (WS_P_OS_AMT > LNCADSCH.COMM_DATA.AMOUNT 
                    && LNCADSCH.COMM_DATA.SUB_CTA_NO == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WAN_LN5713;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "EFG");
        }
        CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        if (LNCADSCH.COMM_DATA.DUE_DT > LNRCONT.MAT_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5706;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[5] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[7] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[9] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (LNCADSCH.COMM_DATA.DUE_DT < SCCGWA.COMM_AREA.AC_DATE 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0132181") 
            && !JIBS_tmp_str[3].equalsIgnoreCase("0132941") 
            && !JIBS_tmp_str[5].equalsIgnoreCase("0132950") 
            && !JIBS_tmp_str[7].equalsIgnoreCase("0139121") 
            && !JIBS_tmp_str[9].equalsIgnoreCase("0662171")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5731;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B211_GET_MAX_TERM_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'G';
        LNRPLPI.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCADSCH.COMM_DATA.TYPE;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NEXT_TERM_NO);
        CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCRPLPI.MAX_DUE_DT);
        if (LNCADSCH.COMM_DATA.FUNC_CD == 'A') {
            if (LNCADSCH.COMM_DATA.VAL_DT < LNCRPLPI.MAX_DUE_DT) {
                CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.CONTRACT_NO);
                CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.TYPE);
                CEP.TRC(SCCGWA, LNCADSCH.COMM_DATA.VAL_DT);
                CEP.TRC(SCCGWA, LNCRPLPI.MAX_DUE_DT);
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5725;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCRPLPI.TERM_MAX > 0) {
                WS_NEXT_TERM_NO = (short) (LNCRPLPI.TERM_MAX + 1);
            } else {
                IBS.init(SCCGWA, LNCRICTL);
                IBS.init(SCCGWA, LNRICTL);
                LNCRICTL.FUNC = 'I';
                LNRICTL.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
                LNRICTL.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
                LNCRICTL.REC_PTR = LNRICTL;
                LNCRICTL.REC_LEN = 425;
                S000_CALL_LNZRICTL();
                if (pgmRtn) return;
                if (LNCADSCH.COMM_DATA.TYPE == K_INTEREST) {
                    WS_NEXT_TERM_NO = LNRICTL.IC_CMP_TERM;
                } else {
                    WS_NEXT_TERM_NO = LNRICTL.P_CMP_TERM;
                }
            }
        }
        if (LNCADSCH.COMM_DATA.FUNC_CD == 'D') {
            if (LNCADSCH.COMM_DATA.DUE_DT != LNCRPLPI.MAX_DUE_DT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5726;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_LAST_TERM_NO = LNCRPLPI.TERM_MAX;
        }
    }
    public void B212_GET_UN_BUILD_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPRIN);
        LNCSPRIN.COMM_DATA.CTA_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNCSPRIN.COMM_DATA.SUF_NO = "" + LNCADSCH.COMM_DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCSPRIN.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSPRIN.COMM_DATA.SUF_NO = "0" + LNCSPRIN.COMM_DATA.SUF_NO;
        LNCSPRIN.COMM_DATA.REPY_TYP = LNCADSCH.COMM_DATA.TYPE;
        S000_CALL_FUNC_LNZSPRIN();
        if (pgmRtn) return;
    }
    public void B220_WRITE_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'A';
        LNRPLPI.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCADSCH.COMM_DATA.TYPE;
        LNRPLPI.KEY.TERM = WS_NEXT_TERM_NO;
        LNRPLPI.VAL_DT = LNCADSCH.COMM_DATA.VAL_DT;
        LNRPLPI.DUE_DT = LNCADSCH.COMM_DATA.DUE_DT;
        LNRPLPI.DUE_REPY_AMT = LNCADSCH.COMM_DATA.AMOUNT;
        if (LNRPLPI.KEY.REPY_MTH == 'I') {
            LNRPLPI.PRIN_AMT = 0;
        } else {
            LNRPLPI.PRIN_AMT = LNCADSCH.COMM_DATA.AMOUNT;
        }
        LNRPLPI.REMARK = LNCADSCH.COMM_DATA.REMARK;
        LNRPLPI.REC_STS = K_NORMAL;
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B230_READUP_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCADSCH.COMM_DATA.TYPE;
        if (LNCADSCH.COMM_DATA.TERM > 0) {
            LNCRPLPI.FUNC = 'R';
            LNRPLPI.KEY.TERM = LNCADSCH.COMM_DATA.TERM;
        } else {
            LNCRPLPI.FUNC = 'P';
            LNRPLPI.VAL_DT = LNCADSCH.COMM_DATA.VAL_DT;
            LNRPLPI.DUE_DT = LNCADSCH.COMM_DATA.DUE_DT;
        }
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_LNZRPLPI_II();
        if (pgmRtn) return;
        if (LNCRPLPI.RETURN_INFO == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRPLPI.KEY.TERM != WS_LAST_TERM_NO) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5726;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B240_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.FUNC = 'D';
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
    }
    public void B250_CONFIRM_SCHEDULE() throws IOException,SQLException,Exception {
        if ((LNCADSCH.COMM_DATA.TYPE == K_PRINCIPAL) 
            && LNCADSCH.COMM_DATA.FUNC_CD == 'A') {
            IBS.init(SCCGWA, LNRPLP2);
            IBS.init(SCCGWA, LNCRPLP2);
            LNCRPLP2.FUNC = 'B';
            LNCRPLP2.OPT = 'O';
            LNRPLP2.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
            LNRPLP2.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
            LNRPLP2.KEY.REPY_MTH = K_INTEREST;
            LNRPLP2.VAL_DT = LNCADSCH.COMM_DATA.VAL_DT;
            LNRPLP2.DUE_DT = LNCADSCH.COMM_DATA.DUE_DT;
            LNCRPLP2.REC_PTR = LNRPLP2;
            LNCRPLP2.REC_LEN = 277;
            S000_CALL_LNZRPLP2();
            if (pgmRtn) return;
            if (LNCRPLP2.RETURN_INFO == 'N' 
                || LNRPLP2.DUE_DT > LNCADSCH.COMM_DATA.DUE_DT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5715;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B260_SYN_UPD_LNTICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCADSCH.COMM_DATA.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCADSCH.COMM_DATA.SUB_CTA_NO;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNCADSCH.COMM_DATA.FUNC_CD == 'A') {
            if (LNCADSCH.COMM_DATA.TYPE == K_INTEREST) {
                if (LNRICTL.IC_CAL_TERM == LNRICTL.IC_CMP_TERM) {
                    LNRICTL.IC_CAL_DUE_DT = LNCADSCH.COMM_DATA.DUE_DT;
                }
                LNRICTL.IC_CMP_TERM = (short) (LNRICTL.IC_CMP_TERM + 1);
                LNRICTL.IC_CMP_VAL_DT = LNCADSCH.COMM_DATA.DUE_DT;
            } else {
                if (LNRICTL.P_CAL_TERM == LNRICTL.P_CMP_TERM) {
                    LNRICTL.P_CAL_DUE_DT = LNCADSCH.COMM_DATA.DUE_DT;
                }
                LNRICTL.P_CMP_TERM = (short) (LNRICTL.P_CMP_TERM + 1);
            }
        } else {
            if (LNCADSCH.COMM_DATA.TYPE == K_INTEREST) {
                LNRICTL.IC_CMP_TERM = (short) (LNRICTL.IC_CMP_TERM - 1);
                LNRICTL.IC_CMP_VAL_DT = LNCADSCH.COMM_DATA.VAL_DT;
            } else {
                LNRICTL.P_CMP_TERM = (short) (LNRICTL.P_CMP_TERM - 1);
            }
        }
        LNCRICTL.FUNC = 'U';
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_CALL_FUNC_LNZSPRIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-SUM-PRIN", LNCSPRIN);
        CEP.TRC(SCCGWA, LNCSPRIN.RC);
        if (LNCSPRIN.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPRIN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCADSCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRPLPI_II() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            if (LNCRPLPI.RETURN_INFO != 'N') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCADSCH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRPLP2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLP2);
        if (LNCRPLP2.RC.RC_CODE != 0) {
            if (LNCRPLP2.RETURN_INFO != 'N') {
