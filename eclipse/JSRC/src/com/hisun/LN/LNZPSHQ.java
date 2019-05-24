package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPSHQ {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAPRD_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    LNZPSHQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZPSHQ_WS_TEMP_VARIABLE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    SCCGWA SCCGWA;
    LNCPSHQ LNCPSHQ;
    public void MP(SCCGWA SCCGWA, LNCPSHQ LNCPSHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPSHQ = LNCPSHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPSHQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_INQUIRE_PROC();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCPSHQ.COMM_DATA.CONTRACT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCPSHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCPSHQ.COMM_DATA.PAY_TYP != 'C' 
            && LNCPSHQ.COMM_DATA.PAY_TYP != 'P' 
            && LNCPSHQ.COMM_DATA.PAY_TYP != 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPSHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_INQUIRE_PROC() throws IOException,SQLException,Exception {
        B200_GET_LOAN_INF();
        if (pgmRtn) return;
        B200_GET_PPMQ_INF();
        if (pgmRtn) return;
        B200_GET_ICTL_INF();
        if (pgmRtn) return;
    }
    public void B200_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void B200_GET_PPMQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        T000_READ_APRD_PROC();
        if (pgmRtn) return;
    }
    public void B200_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        CEP.TRC(SCCGWA, LNCPSHQ.COMM_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCPSHQ.COMM_DATA.SUB_CTA_NO);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCPSHQ.COMM_DATA.SUB_CTA_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        if (LNCPSHQ.COMM_DATA.PAY_TYP == 'P') {
            WS_TEMP_VARIABLE.WS_TERM = LNCPSHQ.COMM_DATA.START_TE;
            WS_TEMP_VARIABLE.WS_DUE_DT = LNCICTLM.REC_DATA.P_CMP_DUE_DT;
        } else {
            WS_TEMP_VARIABLE.WS_TERM = LNCPSHQ.COMM_DATA.STRIN_TE;
            WS_TEMP_VARIABLE.WS_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
        }
        WS_TEMP_VARIABLE.WS_NOTFND_ID = 'N';
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= LNCPSHQ.COMM_DATA.TOTAL_TE 
            && WS_TEMP_VARIABLE.WS_I <= 20; WS_TEMP_VARIABLE.WS_I += 1) {
            if (WS_TEMP_VARIABLE.WS_NOTFND_ID == 'N') {
                B320_READ_LNTPLPI();
                if (pgmRtn) return;
            } else {
                B320_COMP_TERM_DUEDT();
                if (pgmRtn) return;
            }
            LNCPSHQ.COMM_DATA.TERMINFO[WS_TEMP_VARIABLE.WS_I-1].TERMNO = WS_TEMP_VARIABLE.WS_TERM;
            LNCPSHQ.COMM_DATA.TERMINFO[WS_TEMP_VARIABLE.WS_I-1].TERM_DTE = WS_TEMP_VARIABLE.WS_DUE_DT;
            LNCPSHQ.COMM_DATA.TERMINFO[WS_TEMP_VARIABLE.WS_I-1].TERM_AMT = WS_TEMP_VARIABLE.WS_REPY_AMT;
            WS_TEMP_VARIABLE.WS_TERM += 1;
        }
    }
    public void B320_READ_LNTPLPI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLPIM);
        LNCPLPIM.FUNC = '3';
        LNCPLPIM.REC_DATA.KEY.CONTRACT_NO = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        LNCPLPIM.REC_DATA.KEY.SUB_CTA_NO = LNCPSHQ.COMM_DATA.SUB_CTA_NO;
        LNCPLPIM.REC_DATA.KEY.REPY_MTH = LNCPSHQ.COMM_DATA.PAY_TYP;
        LNCPLPIM.REC_DATA.KEY.TERM = WS_TEMP_VARIABLE.WS_TERM;
        S000_CALL_LNZPLPIM();
        if (pgmRtn) return;
        if (LNCPLPIM.RC.RC_RTNCODE == 0) {
            WS_TEMP_VARIABLE.WS_TERM = LNCPLPIM.REC_DATA.KEY.TERM;
            WS_TEMP_VARIABLE.WS_DUE_DT = LNCPLPIM.REC_DATA.DUE_DT;
            WS_TEMP_VARIABLE.WS_REPY_AMT = LNCPLPIM.REC_DATA.DUE_REPY_AMT;
        } else {
            WS_TEMP_VARIABLE.WS_NOTFND_ID = 'Y';
            if (LNCPSHQ.COMM_DATA.PAY_TYP == 'C') {
                B310_GET_INST_AMT();
                if (pgmRtn) return;
            }
            B320_COMP_TERM_DUEDT();
            if (pgmRtn) return;
        }
    }
    public void B320_COMP_TERM_DUEDT() throws IOException,SQLException,Exception {
        if (WS_TEMP_VARIABLE.WS_I == 1) {
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_TEMP_VARIABLE.WS_DUE_DT;
            if (LNRAPRD.CAL_PERD == 0) {
                if (LNRAPRD.CAL_PERD_UNIT == 'D') {
                    LNRAPRD.CAL_PERD = 10;
                } else {
                    LNRAPRD.CAL_PERD = 1;
                }
            }
            if (LNRAPRD.CAL_PERD_UNIT == 'D') {
                SCCCLDT.DAYS = LNRAPRD.CAL_PERD;
            } else {
                SCCCLDT.MTHS = LNRAPRD.CAL_PERD;
            }
            R00_CAL_DATE();
            if (pgmRtn) return;
            WS_TEMP_VARIABLE.WS_DUE_DT = SCCCLDT.DATE2;
        }
    }
    public void B310_GET_INST_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPAIPM);
        LNCPAIPM.FUNC = '3';
        LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCPSHQ.COMM_DATA.CONTRACT_NO;
        LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = LNCPSHQ.COMM_DATA.SUB_CTA_NO;
        LNCPAIPM.REC_DATA.KEY.PHS_NO = LNCICTLM.REC_DATA.IC_CMP_PHS_NO;
        S000_CALL_LNZPAIPM();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_REPY_AMT = LNCPAIPM.REC_DATA.CUR_INST_AMT;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCPSHQ.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCPSHQ.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            LNCPSHQ.RC.RC_APP = LNCLOANM.RC.RC_APP;
            LNCPSHQ.RC.RC_RTNCODE = LNCLOANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCPSHQ.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCPSHQ.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLPIM.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("LN0000") 
            && !JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND)) {
            LNCPSHQ.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCPSHQ.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PAIP-MAINT", LNCPAIPM);
        if (LNCPAIPM.RC.RC_RTNCODE != 0) {
            LNCPSHQ.RC.RC_APP = LNCPAIPM.RC.RC_APP;
            LNCPSHQ.RC.RC_RTNCODE = LNCPAIPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCPSHQ.RC.RC_APP = "SC";
            LNCPSHQ.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_APRD_PROC() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCPSHQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCPSHQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPSHQ=");
            CEP.TRC(SCCGWA, LNCPSHQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
