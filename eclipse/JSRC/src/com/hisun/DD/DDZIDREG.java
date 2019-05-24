package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIDREG {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTDREG_RD;
    boolean pgmRtn = false;
    char K_REQ_STS_NORMAL = 'N';
    char K_REQ_STS_DELETE = 'D';
    String K_HIS_CPB_NAME = "DDCHDREG";
    String K_HIS_REMARKS = "DDTDREG RECORD MAINTAIN";
    String WS_ERR_MSG = " ";
    char WS_REC_CHG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRDREG DDRDREG = new DDRDREG();
    DDRDREG DDRDREGO = new DDRDREG();
    DDCHDREG DDCHDREGO = new DDCHDREG();
    DDCHDREG DDCHDREGN = new DDCHDREG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIDREG DDCIDREG;
    public void MP(SCCGWA SCCGWA, DDCIDREG DDCIDREG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIDREG = DDCIDREG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIDREG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIDREG.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIDREG.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_ACAC_PROC();
        if (pgmRtn) return;
        if (DDCIDREG.OPT == 'I') {
            B010_INQ_DREG_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'A') {
            B020_CRT_DREG_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'U') {
            B030_UPD_DREG_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'S') {
            B040_UPD_DREG_STS_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'T') {
            B050_ACTIVE_DREG_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'D') {
            B060_DEL_DREG_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'R') {
            B070_REU_DREG_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'X') {
            B080_UPD_DREG_STS_PROC();
            if (pgmRtn) return;
        } else if (DDCIDREG.OPT == 'E') {
            B090_SET_DREG_STS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIDREG.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_GET_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCIDREG.DATA.KEY.AC;
        CICQACAC.DATA.CCY_ACAC = DDCIDREG.DATA.KEY.CCY;
        CICQACAC.DATA.CR_FLG = DDCIDREG.DATA.KEY.CCY_TYPE;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B010_INQ_DREG_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRDREG);
        IBS.init(SCCGWA, DDRDREGO);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
    }
    public void B020_CRT_DREG_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, DDRDREG);
            IBS.init(SCCGWA, DDRDREGO);
            DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDRDREG.KEY.APP_DATE = DDCIDREG.DATA.DATE;
            DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            DDRDREG.STS = DDCIDREG.DATA.STS;
            DDRDREG.FLG = DDCIDREG.DATA.FLG;
            DDRDREG.BAL = DDCIDREG.DATA.BAL;
            DDRDREG.INT = DDCIDREG.DATA.INT;
            DDRDREG.INT_BAL = DDCIDREG.DATA.INT_BAL;
            DDRDREG.OPN_DT = DDCIDREG.DATA.OPN_DT;
            DDRDREG.BR = DDCIDREG.DATA.BR;
            DDRDREG.W_DT = DDCIDREG.DATA.W_DT;
            DDRDREG.W_BR = DDCIDREG.DATA.W_BR;
            DDRDREG.W_TLR = DDCIDREG.DATA.W_TLR;
            DDRDREG.D_DT = DDCIDREG.DATA.D_DT;
            DDRDREG.D_BR = DDCIDREG.DATA.D_BR;
            DDRDREG.D_TLR = DDCIDREG.DATA.D_TLR;
            DDRDREG.NTF_FLG = DDCIDREG.DATA.NTF_FLG;
            DDRDREG.NTF_DT = DDCIDREG.DATA.NTF_DT;
            DDRDREG.NTF_NUM = DDCIDREG.DATA.NTF_NUM;
            DDRDREG.RCD_STS = K_REQ_STS_NORMAL;
            T000_WRITE_DDTDREG();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
            R000_TRANS_DATA_BACK();
            if (pgmRtn) return;
            R000_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        } else {
            if (DDRDREG.RCD_STS == K_REQ_STS_DELETE) {
                R000_TRANS_NFHIS_OLD();
                if (pgmRtn) return;
                DDRDREG.KEY.APP_DATE = DDCIDREG.DATA.DATE;
                DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                DDRDREG.STS = DDCIDREG.DATA.STS;
                DDRDREG.BAL = DDCIDREG.DATA.BAL;
                DDRDREG.FLG = DDCIDREG.DATA.FLG;
                if (DDCIDREG.DATA.STS == '1') {
                    DDRDREG.W_DT = DDCIDREG.DATA.W_DT;
                    DDRDREG.W_BR = DDCIDREG.DATA.W_BR;
                    DDRDREG.W_TLR = DDCIDREG.DATA.W_TLR;
                } else {
                    DDRDREG.D_DT = DDCIDREG.DATA.D_DT;
                    DDRDREG.D_BR = DDCIDREG.DATA.D_BR;
                    DDRDREG.D_TLR = DDCIDREG.DATA.D_TLR;
                }
                DDRDREG.RCD_STS = K_REQ_STS_NORMAL;
                T000_REWRITE_DDTDREG();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
                R000_TRANS_DATA_BACK();
                if (pgmRtn) return;
                R000_TRANS_NFHIS_NEW();
                if (pgmRtn) return;
                R000_NON_FIN_HIS_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_STS_NORMAL, DDCIDREG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_UPD_DREG_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.STS = DDCIDREG.DATA.STS;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRDREG.FLG = DDCIDREG.DATA.FLG;
        DDRDREG.BAL = DDCIDREG.DATA.BAL;
        DDRDREG.INT = DDCIDREG.DATA.INT;
        DDRDREG.INT_BAL = DDCIDREG.DATA.INT_BAL;
        DDRDREG.D_DT = DDCIDREG.DATA.D_DT;
        DDRDREG.D_BR = DDCIDREG.DATA.D_BR;
        DDRDREG.D_TLR = DDCIDREG.DATA.D_TLR;
        DDRDREG.NTF_FLG = DDCIDREG.DATA.NTF_FLG;
        DDRDREG.NTF_DT = DDCIDREG.DATA.NTF_DT;
        DDRDREG.NTF_NUM = DDCIDREG.DATA.NTF_NUM;
        DDRDREG.RCD_STS = 'N';
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPD_DREG_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRDREG.RCD_STS);
        if (DDRDREG.RCD_STS == K_REQ_STS_DELETE) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_STS_DELETE, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRDREG.RCD_STS = K_REQ_STS_DELETE;
        DDRDREG.STS = DDCIDREG.DATA.STS;
        CEP.TRC(SCCGWA, DDRDREG.STS);
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_ACTIVE_DREG_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDRDREG.STS == '5' 
            || DDRDREG.STS == '6' 
            || DDRDREG.STS == '7') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_STS_ACT, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRDREG.STS);
        if (DDRDREG.STS == '2') {
            DDRDREG.STS = '6';
        }
        if (DDRDREG.STS == '3' 
            || DDRDREG.STS == '4') {
            DDRDREG.STS = '7';
        }
        if (DDRDREG.STS == '9') {
            DDRDREG.STS = '0';
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DDRDREG.FLG = 'O';
        } else {
            DDRDREG.FLG = 'B';
        }
        DDRDREG.N_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.N_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDREG.N_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDREG.RCD_STS = K_REQ_STS_DELETE;
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B060_DEL_DREG_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B070_REU_DREG_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRDREG);
        IBS.init(SCCGWA, DDRDREGO);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUPD_DDTDREG();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
    }
    public void B080_UPD_DREG_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRDREG.RCD_STS);
        if (DDRDREG.RCD_STS == K_REQ_STS_DELETE) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_STS_DELETE, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRDREG.STS);
        if (DDRDREG.STS != '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_PRE_DORM_AC, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        DDRDREG.RCD_STS = K_REQ_STS_DELETE;
        DDRDREG.STS = '5';
        DDRDREG.N_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.N_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDREG.N_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B090_SET_DREG_STS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDREG.DATA.FLG);
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_DDTDREG_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_NFHIS_OLD();
        if (pgmRtn) return;
        if (DDRDREG.STS == '1') {
            DDRDREG.STS = '5';
            DDRDREG.N_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDREG.N_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRDREG.N_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRDREG, DDRDREGO);
        R000_TRANS_DATA_BACK();
        if (pgmRtn) return;
        R000_TRANS_NFHIS_NEW();
        if (pgmRtn) return;
        R000_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.BR);
        if (DDCIDREG.OPT == 'A' 
            && DDCIDREG.DATA.BR == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_M_INPUT, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIDREG.DATA.KEY.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_R_UPD_DDTDREG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.STS);
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
        T000_READUPD_DDTDREG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_BACK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DATA BACK");
        CEP.TRC(SCCGWA, "1");
        DDCIDREG.DATA.KEY.AC = CICQACAC.DATA.AGR_NO;
        DDCIDREG.DATA.KEY.CCY = CICQACAC.DATA.CCY_ACAC;
        DDCIDREG.DATA.KEY.CCY_TYPE = CICQACAC.DATA.CR_FLG;
        DDCIDREG.DATA.DATE = DDRDREGO.KEY.APP_DATE;
        DDCIDREG.DATA.JRNNO = DDRDREGO.KEY.JRNNO;
        DDCIDREG.DATA.STS = DDRDREGO.STS;
        DDCIDREG.DATA.BAL = DDRDREGO.BAL;
        DDCIDREG.DATA.INT = DDRDREGO.INT;
        DDCIDREG.DATA.INT_BAL = DDRDREGO.INT_BAL;
        DDCIDREG.DATA.FLG = DDRDREGO.FLG;
        DDCIDREG.DATA.OPN_DT = DDRDREGO.OPN_DT;
        DDCIDREG.DATA.BR = DDRDREGO.BR;
        DDCIDREG.DATA.W_DT = DDRDREGO.W_DT;
        DDCIDREG.DATA.W_BR = DDRDREGO.W_BR;
        DDCIDREG.DATA.W_TLR = DDRDREGO.W_TLR;
        DDCIDREG.DATA.D_DT = DDRDREGO.D_DT;
        DDCIDREG.DATA.D_BR = DDRDREGO.D_BR;
        DDCIDREG.DATA.D_TLR = DDRDREGO.D_TLR;
        DDCIDREG.DATA.T_DT = DDRDREGO.T_DT;
        DDCIDREG.DATA.T_BR = DDRDREGO.T_BR;
        DDCIDREG.DATA.T_TLR = DDRDREGO.T_TLR;
        DDCIDREG.DATA.P_DT = DDRDREGO.P_DT;
        DDCIDREG.DATA.P_BR = DDRDREGO.P_BR;
        DDCIDREG.DATA.P_TLR = DDRDREGO.P_TLR;
        DDCIDREG.DATA.N_DT = DDRDREGO.N_DT;
        DDCIDREG.DATA.N_BR = DDRDREGO.N_BR;
        DDCIDREG.DATA.N_TLR = DDRDREGO.N_TLR;
        DDCIDREG.DATA.NTF_FLG = DDRDREGO.NTF_FLG;
        DDCIDREG.DATA.NTF_DT = DDRDREGO.NTF_DT;
        DDCIDREG.DATA.NTF_NUM = DDRDREGO.NTF_NUM;
        DDCIDREG.DATA.FLG1 = DDRDREGO.FLG1;
        DDCIDREG.DATA.FLG2 = DDRDREGO.FLG2;
        DDCIDREG.DATA.RCD_STS = DDRDREGO.RCD_STS;
        DDCIDREG.DATA.REMARKS = DDRDREGO.REMARKS;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHDREGO);
        DDCHDREGO.AC = DDRDREG.KEY.AC;
        DDCHDREGO.DATE = DDRDREG.KEY.APP_DATE;
        DDCHDREGO.JRNNO = DDRDREG.KEY.JRNNO;
        DDCHDREGO.STS = DDRDREG.STS;
        DDCHDREGO.BAL = DDRDREG.BAL;
        DDCHDREGO.INT = DDRDREG.INT;
        DDCHDREGO.INT_BAL = DDRDREG.INT_BAL;
        DDCHDREGO.FLG = DDRDREG.FLG;
        DDCHDREGO.OPN_DT = DDRDREG.OPN_DT;
        DDCHDREGO.BR = DDRDREG.BR;
        DDCHDREGO.W_DT = DDRDREG.W_DT;
        DDCHDREGO.W_BR = DDRDREG.W_BR;
        DDCHDREGO.W_TLR = DDRDREG.W_TLR;
        DDCHDREGO.D_DT = DDRDREG.D_DT;
        DDCHDREGO.D_BR = DDRDREG.D_BR;
        DDCHDREGO.D_TLR = DDRDREG.D_TLR;
        DDCHDREGO.T_DT = DDRDREG.T_DT;
        DDCHDREGO.T_BR = DDRDREG.T_BR;
        DDCHDREGO.T_TLR = DDRDREG.T_TLR;
        DDCHDREGO.P_DT = DDRDREG.P_DT;
        DDCHDREGO.P_BR = DDRDREG.P_BR;
        DDCHDREGO.P_TLR = DDRDREG.P_TLR;
        DDCHDREGO.N_DT = DDRDREG.N_DT;
        DDCHDREGO.N_BR = DDRDREG.N_BR;
        DDCHDREGO.N_TLR = DDRDREG.N_TLR;
        DDCHDREGO.NTF_FLG = DDRDREG.NTF_FLG;
        DDCHDREGO.NTF_DT = DDRDREG.NTF_DT;
        DDCHDREGO.NTF_NUM = DDRDREG.NTF_NUM;
        DDCHDREGO.FLG1 = DDRDREG.FLG1;
        DDCHDREGO.FLG2 = DDRDREG.FLG2;
        DDCHDREGO.RCD_STS = DDRDREG.RCD_STS;
        DDCHDREGO.REMARKS = DDRDREG.REMARKS;
        CEP.TRC(SCCGWA, DDCHDREGO);
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCHDREGN);
        DDCHDREGN.AC = DDRDREG.KEY.AC;
        DDCHDREGN.DATE = DDRDREG.KEY.APP_DATE;
        DDCHDREGN.JRNNO = DDRDREG.KEY.JRNNO;
        DDCHDREGN.STS = DDRDREG.STS;
        DDCHDREGN.BAL = DDRDREG.BAL;
        DDCHDREGN.INT = DDRDREG.INT;
        DDCHDREGN.INT_BAL = DDRDREG.INT_BAL;
        DDCHDREGN.FLG = DDRDREG.FLG;
        DDCHDREGN.OPN_DT = DDRDREG.OPN_DT;
        DDCHDREGN.BR = DDRDREG.BR;
        DDCHDREGN.W_DT = DDRDREG.W_DT;
        DDCHDREGN.W_BR = DDRDREG.W_BR;
        DDCHDREGN.W_TLR = DDRDREG.W_TLR;
        DDCHDREGN.D_DT = DDRDREG.D_DT;
        DDCHDREGN.D_BR = DDRDREG.D_BR;
        DDCHDREGN.D_TLR = DDRDREG.D_TLR;
        DDCHDREGN.T_DT = DDRDREG.T_DT;
        DDCHDREGN.T_BR = DDRDREG.T_BR;
        DDCHDREGN.T_TLR = DDRDREG.T_TLR;
        DDCHDREGN.P_DT = DDRDREG.P_DT;
        DDCHDREGN.P_BR = DDRDREG.P_BR;
        DDCHDREGN.P_TLR = DDRDREG.P_TLR;
        DDCHDREGN.N_DT = DDRDREG.N_DT;
        DDCHDREGN.N_BR = DDRDREG.N_BR;
        DDCHDREGN.N_TLR = DDRDREG.N_TLR;
        DDCHDREGN.NTF_FLG = DDRDREG.NTF_FLG;
        DDCHDREGN.NTF_DT = DDRDREG.NTF_DT;
        DDCHDREGN.NTF_NUM = DDRDREG.NTF_NUM;
        DDCHDREGN.FLG1 = DDRDREG.FLG1;
        DDCHDREGN.FLG2 = DDRDREG.FLG2;
        DDCHDREGN.RCD_STS = DDRDREG.RCD_STS;
        DDCHDREGN.REMARKS = DDRDREG.REMARKS;
        CEP.TRC(SCCGWA, DDCHDREGN);
    }
    public void R000_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.AC = DDCIDREG.DATA.KEY.AC;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        if (DDCIDREG.OPT == 'E' 
            || DDCIDREG.OPT == 'X') {
            BPCPNHIS.INFO.TX_TYP_CD = "P402";
        }
        if (DDCIDREG.OPT == 'T') {
            BPCPNHIS.INFO.TX_TYP_CD = "PB07";
        }
        if (DDRDREG.STS == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P401";
        }
        if (DDCIDREG.OPT == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (DDCIDREG.OPT == 'U'
            || DDCIDREG.OPT == 'S'
            || DDCIDREG.OPT == 'T'
            || DDCIDREG.OPT == 'X'
            || DDCIDREG.OPT == 'E') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DDCHDREGO;
            BPCPNHIS.INFO.NEW_DAT_PT = DDCHDREGN;
        } else if (DDCIDREG.OPT == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.eqWhere = "AC";
        DDTDREG_RD.fst = true;
        DDTDREG_RD.order = "APP_DATE, JRNNO DESC";
        IBS.READ(SCCGWA, DDRDREG, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTDREG() throws IOException,SQLException,Exception {
        DDRDREG.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDREG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRDREG, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_EXIST, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTDREG() throws IOException,SQLException,Exception {
        DDRDREG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDREG.OTH_AC = DDCIDREG.DATA.OTH_AC;
        DDRDREG.OTH_ACNM = DDCIDREG.DATA.OTH_ACNM;
        DDRDREG.OTH_BR = DDCIDREG.DATA.OTH_BR;
        DDRDREG.OTH_BRNM = DDCIDREG.DATA.OTH_BRNM;
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        IBS.REWRITE(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void T000_READUPD_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.eqWhere = "AC";
        DDTDREG_RD.upd = true;
        IBS.READ(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void T000_DELETE_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        IBS.DELETE(SCCGWA, DDRDREG, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND, DDCIDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIDREG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIDREG=");
            CEP.TRC(SCCGWA, DDCIDREG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
