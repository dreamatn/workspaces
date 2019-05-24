package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIDORM {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTDORM_RD;
    boolean pgmRtn = false;
    char K_REQ_STS_NORMAL = 'N';
    char K_REQ_STS_DELETE = 'D';
    char K_REQ_EXC_UNDO = 'W';
    char K_REQ_EXC_FINISH = 'S';
    String K_HIS_CPB_NAME = "DDCHDORM";
    String K_HIS_REMARKS = "DDTDORM RECORD MAINTAIN";
    String WS_ERR_MSG = " ";
    char WS_REC_CHG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRDORM DDRDORM = new DDRDORM();
    DDRDORM DDRDORMO = new DDRDORM();
    DDCHDORM DDCHDORMO = new DDCHDORM();
    DDCHDORM DDCHDORMN = new DDCHDORM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    DDCIDORM DDCIDORM;
    public void MP(SCCGWA SCCGWA, DDCIDORM DDCIDORM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIDORM = DDCIDORM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIDORM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIDORM.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIDORM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCIDORM.OPT == 'I') {
            B010_INQ_DORM_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDORM.OPT == 'A') {
            B020_CRT_DORM_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDORM.OPT == 'U') {
            B030_UPD_DORM_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDORM.OPT == 'S') {
            B040_UPD_DORM_STS_PROC();
            if (pgmRtn) return;
        } else if (DDCIDORM.OPT == 'D') {
            B050_DEL_DORM_INF_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIDORM.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_DORM_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRDORM);
        IBS.init(SCCGWA, DDRDORMO);
        DDRDORM.KEY.BR_NO = DDCIDORM.DATA.BR_NO;
        DDRDORM.KEY.DATE = DDCIDORM.DATA.DATE;
        DDRDORM.KEY.TYPE = DDCIDORM.DATA.TYPE;
        T000_READ_DDTDORM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
    }
    public void B020_CRT_DORM_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDORM_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, DDRDORM);
            IBS.init(SCCGWA, DDRDORMO);
            DDRDORM.KEY.DATE = DDCIDORM.DATA.DATE;
            DDRDORM.KEY.BR_NO = DDCIDORM.DATA.BR_NO;
            DDRDORM.KEY.TYPE = DDCIDORM.DATA.TYPE;
            DDRDORM.END_DT = DDCIDORM.DATA.END_DT;
            DDRDORM.TRS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDORM.TRS_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRDORM.AUT_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            DDRDORM.REMARKS = DDCIDORM.DATA.REMARKS;
            DDRDORM.FLG1 = DDCIDORM.DATA.FLG1;
            DDRDORM.FLG2 = DDCIDORM.DATA.FLG2;
            DDRDORM.STS = K_REQ_STS_NORMAL;
            DDRDORM.TSK_STS = K_REQ_EXC_UNDO;
            T000_WRITE_DDTDORM();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
            R000_TRANS_DATA_BACK();
            if (pgmRtn) return;
            R000_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        } else {
            if (DDRDORM.STS == K_REQ_STS_DELETE) {
                R000_TRANS_NFHIS_OLD();
                if (pgmRtn) return;
                DDRDORM.STS = K_REQ_STS_NORMAL;
                DDRDORM.TRS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                DDRDORM.TRS_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRDORM.AUT_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
                CEP.TRC(SCCGWA, "TESTING");
                T000_REWRITE_DDTDORM();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
                R000_TRANS_DATA_BACK();
                if (pgmRtn) return;
                R000_TRANS_NFHIS_NEW();
                if (pgmRtn) return;
                R000_NON_FIN_HIS_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_STS_NORMAL, DDCIDORM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_UPD_DORM_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDORM_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_NOTFND, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_COMPARE_UPDATE_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_REC_CHG_FLG);
        if (WS_REC_CHG_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        DDRDORM.END_DT = DDCIDORM.DATA.END_DT;
        DDRDORM.REMARKS = DDCIDORM.DATA.REMARKS;
        T000_REWRITE_DDTDORM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPD_DORM_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDORM_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_NOTFND, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDRDORM.STS == K_REQ_STS_DELETE) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_STS_DELETE, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        if (DDCIDORM.DATA.DEL_BR != 0) {
            DDRDORM.DEL_BR = DDCIDORM.DATA.DEL_BR;
        }
        if (DDCIDORM.DATA.DEL_TLR.trim().length() > 0) {
            DDRDORM.DEL_TLR = DDCIDORM.DATA.DEL_TLR;
        }
        if (DDCIDORM.DATA.AUT_TLR2.trim().length() > 0) {
            DDRDORM.AUT_TLR2 = DDCIDORM.DATA.AUT_TLR2;
        }
        if (DDCIDORM.DATA.STS != ' ') {
            DDRDORM.STS = DDCIDORM.DATA.STS;
        }
        if (DDCIDORM.DATA.TSK_STS != ' ') {
            DDRDORM.TSK_STS = DDCIDORM.DATA.TSK_STS;
        }
        T000_REWRITE_DDTDORM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_DEL_DORM_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDORM_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_NOTFND, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_DDTDORM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDORM, DDRDORMO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDORM.DATA.DATE);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.BR_NO);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.TYPE);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.END_DT);
        if (DDCIDORM.DATA.BR_NO == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_M_INPUT, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIDORM.DATA.TYPE == ' ' 
            || (DDCIDORM.DATA.TYPE != '1' 
            && DDCIDORM.DATA.TYPE != '2')) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_REQ_TYP_INVALID, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIDORM.OPT == 'I'
            || DDCIDORM.OPT == 'S'
            || DDCIDORM.OPT == 'D') {
            if (DDCIDORM.DATA.DATE == 0 
                || DDCIDORM.DATA.DATE > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_REQ_DT_INVALID, DDCIDORM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (DDCIDORM.OPT == 'A'
            || DDCIDORM.OPT == 'U') {
            if (DDCIDORM.DATA.END_DT == 0 
                || DDCIDORM.DATA.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_END_DT_INVALID, DDCIDORM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void R000_R_UPD_DDTDORM_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDORM.DATA.BR_NO);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.DATE);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.TYPE);
        CEP.TRC(SCCGWA, DDCIDORM.DATA.STS);
        IBS.init(SCCGWA, DDRDORM);
        DDRDORM.KEY.BR_NO = DDCIDORM.DATA.BR_NO;
        DDRDORM.KEY.DATE = DDCIDORM.DATA.DATE;
        DDRDORM.KEY.TYPE = DDCIDORM.DATA.TYPE;
        T000_R_UPD_DDTDORM();
        if (pgmRtn) return;
    }
    public void R000_COMPARE_UPDATE_DATA() throws IOException,SQLException,Exception {
        if (DDCIDORM.DATA.END_DT != DDRDORM.END_DT) {
            WS_REC_CHG_FLG = 'Y';
        }
        if (!DDCIDORM.DATA.REMARKS.equalsIgnoreCase(DDRDORM.REMARKS)) {
            WS_REC_CHG_FLG = 'Y';
        }
    }
    public void R000_TRANS_DATA_BACK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DATA BACK");
        CEP.TRC(SCCGWA, "1");
        DDCIDORM.DATA.END_DT = DDRDORMO.END_DT;
        DDCIDORM.DATA.TRS_BR = DDRDORMO.TRS_BR;
        DDCIDORM.DATA.TRS_TLR = DDRDORMO.TRS_TLR;
        DDCIDORM.DATA.AUT_TLR1 = DDRDORMO.AUT_TLR1;
        DDCIDORM.DATA.DEL_BR = DDRDORMO.DEL_BR;
        DDCIDORM.DATA.DEL_TLR = DDRDORMO.DEL_TLR;
        DDCIDORM.DATA.AUT_TLR2 = DDRDORMO.AUT_TLR2;
        DDCIDORM.DATA.REMARKS = DDRDORMO.REMARKS;
        DDCIDORM.DATA.FLG1 = DDRDORMO.FLG1;
        DDCIDORM.DATA.FLG2 = DDRDORMO.FLG2;
        DDCIDORM.DATA.STS = DDRDORMO.STS;
        DDCIDORM.DATA.TSK_STS = DDRDORMO.TSK_STS;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHDORMO);
        DDCHDORMO.DATE = DDRDORM.KEY.DATE;
        DDCHDORMO.BR_NO = DDRDORM.KEY.BR_NO;
        DDCHDORMO.TYPE = DDRDORM.KEY.TYPE;
        DDCHDORMO.END_DT = DDRDORM.END_DT;
        DDCHDORMO.TRS_BR = DDRDORM.TRS_BR;
        DDCHDORMO.TRS_TLR = DDRDORM.TRS_TLR;
        DDCHDORMO.AUT_TLR1 = DDRDORM.AUT_TLR1;
        DDCHDORMO.DEL_BR = DDRDORM.DEL_BR;
        DDCHDORMO.DEL_TLR = DDRDORM.DEL_TLR;
        DDCHDORMO.AUT_TLR2 = DDRDORM.AUT_TLR2;
        DDCHDORMO.REMARKS = DDRDORM.REMARKS;
        DDCHDORMO.FLG1 = DDRDORM.FLG1;
        DDCHDORMO.FLG2 = DDRDORM.FLG2;
        DDCHDORMO.STS = DDRDORM.STS;
        DDCHDORMO.TSK_STS = DDRDORM.TSK_STS;
        DDCHDORMO.CRT_DT = DDRDORM.CRT_DT;
        DDCHDORMO.CRT_TLR = DDRDORM.CRT_TLR;
        DDCHDORMO.UPD_DT = DDRDORM.UPD_DT;
        DDCHDORMO.UPD_TLR = DDRDORM.UPD_TLR;
        DDCHDORMO.TS = DDRDORM.TS;
        CEP.TRC(SCCGWA, DDCHDORMO);
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHDORMN);
        DDCHDORMN.DATE = DDRDORM.KEY.DATE;
        DDCHDORMN.BR_NO = DDRDORM.KEY.BR_NO;
        DDCHDORMN.TYPE = DDRDORM.KEY.TYPE;
        DDCHDORMN.END_DT = DDRDORM.END_DT;
        DDCHDORMN.TRS_BR = DDRDORM.TRS_BR;
        DDCHDORMN.TRS_TLR = DDRDORM.TRS_TLR;
        DDCHDORMN.AUT_TLR1 = DDRDORM.AUT_TLR1;
        DDCHDORMN.DEL_BR = DDRDORM.DEL_BR;
        DDCHDORMN.DEL_TLR = DDRDORM.DEL_TLR;
        DDCHDORMN.AUT_TLR2 = DDRDORM.AUT_TLR2;
        DDCHDORMN.REMARKS = DDRDORM.REMARKS;
        DDCHDORMN.FLG1 = DDRDORM.FLG1;
        DDCHDORMN.FLG2 = DDRDORM.FLG2;
        DDCHDORMN.STS = DDRDORM.STS;
        DDCHDORMN.TSK_STS = DDRDORM.TSK_STS;
        DDCHDORMN.CRT_DT = DDRDORM.CRT_DT;
        DDCHDORMN.CRT_TLR = DDRDORM.CRT_TLR;
        DDCHDORMN.UPD_DT = DDRDORM.UPD_DT;
        DDCHDORMN.UPD_TLR = DDRDORM.UPD_TLR;
        DDCHDORMN.TS = DDRDORM.TS;
        CEP.TRC(SCCGWA, DDCHDORMN);
    }
    public void R000_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (DDCIDORM.OPT == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (DDCIDORM.OPT == 'U'
            || DDCIDORM.OPT == 'S') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DDCHDORMO;
            BPCPNHIS.INFO.NEW_DAT_PT = DDCHDORMN;
        } else if (DDCIDORM.OPT == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTDORM() throws IOException,SQLException,Exception {
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        IBS.READ(SCCGWA, DDRDORM, DDTDORM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_NOTFND, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTDORM() throws IOException,SQLException,Exception {
        DDRDORM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDORM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        DDTDORM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRDORM, DDTDORM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_EXIST, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTDORM() throws IOException,SQLException,Exception {
        DDRDORM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDORM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        IBS.REWRITE(SCCGWA, DDRDORM, DDTDORM_RD);
    }
    public void T000_DELETE_DDTDORM() throws IOException,SQLException,Exception {
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        IBS.DELETE(SCCGWA, DDRDORM, DDTDORM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DORM_REC_NOTFND, DDCIDORM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_R_UPD_DDTDORM() throws IOException,SQLException,Exception {
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        DDTDORM_RD.upd = true;
        IBS.READ(SCCGWA, DDRDORM, DDTDORM_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIDORM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIDORM=");
            CEP.TRC(SCCGWA, DDCIDORM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
