package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRATQ {
    String JIBS_tmp_str[] = new String[10];
    brParm LNTRATH_BR = new brParm();
    boolean pgmRtn = false;
    char LNZRATQ_FILLER1 = ' ';
    double WS_RATE = 0;
    int WS_RATE_DATE = 0;
    int WS_NEXT_RATE_DATE = 0;
    int WS_GWA_AC_DATE = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRRATH LNRRATH = new LNRRATH();
    LNRCONT LNRCONT = new LNRCONT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRATG LNCRATG = new LNCRATG();
    SCCGWA SCCGWA;
    LNCRATQ LNCRATQ;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, LNCRATQ LNCRATQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRATQ = LNCRATQ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRATQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRATQ.RC.RC_APP = "LN";
        LNCRATQ.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, WS_GWA_AC_DATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_GET_LOAN_INF();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCRATQ.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCRATQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATQ.COMM_DATA.RATE_TYPE != 'N' 
            && LNCRATQ.COMM_DATA.RATE_TYPE != 'O' 
            && LNCRATQ.COMM_DATA.RATE_TYPE != 'L' 
            && LNCRATQ.COMM_DATA.RATE_TYPE != 'P') {
            CEP.TRC(SCCGWA, "RATE-TYPE INTPUT ERROR:");
            CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.RATE_TYPE);
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCRATQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_GET_LOAN_INF() throws IOException,SQLException,Exception {
        B021_GET_CONTRACT_REC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.HAND_CHG_RATE = LNCAPRDM.REC_DATA.HAND_CHG_RATE;
    }
    public void B000_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
        if (LNCRATQ.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATQ.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCRATQ.COMM_DATA.RATE_TYPE == 'N') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_RAT_DT;
            WS_NEXT_RATE_DATE = LNCRATNM.REC_DATA.NEXT_FLT_DT;
        } else if (LNCRATQ.COMM_DATA.RATE_TYPE == 'O') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_PO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
            WS_NEXT_RATE_DATE = 99999999;
        } else if (LNCRATQ.COMM_DATA.RATE_TYPE == 'L') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_IO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
            WS_NEXT_RATE_DATE = 99999999;
        } else if (LNCRATQ.COMM_DATA.RATE_TYPE == 'P') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_FO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
            WS_NEXT_RATE_DATE = 99999999;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRATQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_BRW_RATE_HIS();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.RATE = WS_RATE;
    }
    public void B021_GET_CONTRACT_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_SRC_LNZRCONT();
        if (pgmRtn) return;
    }
    public void S000_CALL_SRC_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            if (LNCRCONT.RETURN_INFO != 'N') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_BRW_RATE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATQ.COMM_DATA.VAL_DATE);
        LNRRATH.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
        if (LNCRATQ.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATQ.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = LNCRATQ.COMM_DATA.RATE_TYPE;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATQ.COMM_DATA.VAL_DATE;
        CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRATH.INT_RAT);
        if (LNRRATH.RATE_KIND == 'D') {
            WS_RATE = LNRRATH.INT_RAT * 360 / 100;
        }
        if (LNRRATH.RATE_KIND == 'M') {
            WS_RATE = LNRRATH.INT_RAT * 12 / 10;
        }
        if (LNRRATH.RATE_KIND == 'Y') {
            WS_RATE = LNRRATH.INT_RAT;
        }
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
    }
    public void B200_GEN_LOAN_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATG);
        LNCRATG.COMM_DATA.FUNC_CODE = 'I';
        LNCRATG.COMM_DATA.LN_AC = LNCRATQ.COMM_DATA.LN_AC;
        LNCRATG.COMM_DATA.SUF_NO = LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATG.COMM_DATA.RATE_TYPE = LNCRATQ.COMM_DATA.RATE_TYPE;
        LNCRATG.COMM_DATA.VAL_DATE = WS_NEXT_RATE_DATE;
        S000_CALL_LNZRATG();
        if (pgmRtn) return;
        LNCRATQ.COMM_DATA.RATE = LNCRATG.COMM_DATA.RATE;
        if (LNCRATQ.COMM_DATA.RATE == 0 
            && LNCRATQ.COMM_DATA.RATE_TYPE == 'L') {
            LNCRATG.COMM_DATA.FUNC_CODE = 'I';
            LNCRATG.COMM_DATA.LN_AC = LNCRATQ.COMM_DATA.LN_AC;
            LNCRATG.COMM_DATA.SUF_NO = LNCRATQ.COMM_DATA.SUF_NO;
            LNCRATG.COMM_DATA.RATE_TYPE = 'O';
            LNCRATG.COMM_DATA.VAL_DATE = WS_NEXT_RATE_DATE;
            S000_CALL_LNZRATG();
            if (pgmRtn) return;
            LNCRATQ.COMM_DATA.RATE = LNCRATG.COMM_DATA.RATE;
        }
    }
    public void T00_STARTBR_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT <= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T00_READNEXT_LNTRATH() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            T00_ENDBR_LNTRATH();
            if (pgmRtn) return;
            LNRRATH.INT_RAT = 0;
            LNCRATQ.COMM_DATA.RATE = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATQ.RC);
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            IBS.init(SCCGWA, LNCRCMMT);
            IBS.init(SCCGWA, LNRCMMT);
            LNCRCMMT.FUNC = 'I';
            LNRCMMT.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
            S000_CALL_LNZRCMMT();
            if (pgmRtn) return;
            if (LNCRCMMT.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, LNCRATNM);
                LNCRATNM.FUNC = '3';
                LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCRATQ.COMM_DATA.LN_AC;
                LNCRATNM.REC_DATA.KEY.ACTV_DT = LNRCMMT.AVAIL_START_DATE;
                S000_CALL_LNZRATNM();
                if (pgmRtn) return;
                LNCRATQ.COMM_DATA.RATE = LNCRATNM.REC_DATA.ALL_IN_RATE;
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATN-MAINT", LNCRATNM);
        CEP.TRC(SCCGWA, LNCRATNM.RC);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.KEY.ACTV_DT);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCRATQ.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCRATQ.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATE-GEN", LNCRATG);
        if (LNCRATG.RC.RC_RTNCODE != 0) {
            LNCRATQ.RC.RC_APP = LNCRATG.RC.RC_APP;
            LNCRATQ.RC.RC_RTNCODE = LNCRATG.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1235;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        CEP.TRC(SCCGWA, LNCRCMMT.RC);
        CEP.TRC(SCCGWA, LNCRCMMT.RETURN_INFO);
        if ((LNCRCMMT.RC.RC_CODE != 0) 
            && (LNCRCMMT.RETURN_INFO != 'N')) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRATQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCRATQ=");
            CEP.TRC(SCCGWA, LNCRATQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
