package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTFCOM {
    DBParm BPTFCOM_RD;
    brParm BPTFCOM_BR = new brParm();
    boolean pgmRtn = false;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFCOM BPRFCOM = new BPRFCOM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCTFCOM BPCTFCOM;
    BPVFEXP BPVVCOM;
    public void MP(SCCGWA SCCGWA, BPCTFCOM BPCTFCOM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTFCOM = BPCTFCOM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTFCOM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPVVCOM = (BPVFEXP) BPCTFCOM.INFO.POINTER;
        IBS.init(SCCGWA, BPCTFCOM.RC);
        CEP.TRC(SCCGWA, BPCTFCOM.INFO.REC_LEN);
        CEP.TRC(SCCGWA, BPVVCOM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTFCOM.INFO.FUNC);
        R000_TRANS_DATA_TO_BPRFCOM();
        if (pgmRtn) return;
        if (BPCTFCOM.INFO.FUNC == '0') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.FUNC == '4') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.FUNC == '3') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.FUNC == '1') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.FUNC == '2') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.FUNC == '5') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTFCOM.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        R000_TRANS_DATA_TO_BPVFCOM();
        if (pgmRtn) return;
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFCOM();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFCOM_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FARM_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FARM_NOTFND, BPCTFCOM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFCOM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFCOM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTFCOM();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTFCOM.INFO.OPT == '0') {
            T000_STARTBR_BPTFCOM();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.OPT == '1') {
            T000_READNEXT_BPTFCOM();
            if (pgmRtn) return;
        } else if (BPCTFCOM.INFO.OPT == '2') {
            T000_ENDBR_BPTFCOM();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT (" + BPCTFCOM.INFO.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void R000_TRANS_DATA_TO_BPRFCOM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCOM);
        BPRFCOM.KEY.FEE_CODE = BPVVCOM.KEY.FEE_CODE;
        BPRFCOM.KEY.REG_CODE = BPVVCOM.KEY.REG_CODE;
        BPRFCOM.KEY.CHN_NO = BPVVCOM.KEY.CHN_NO;
        BPRFCOM.KEY.FREE_FMT = BPVVCOM.KEY.FREE_FMT;
        BPRFCOM.EFF_DATE = BPVVCOM.EFF_DATE;
        BPRFCOM.EXP_DATE = BPVVCOM.EXP_DATE;
        BPRFCOM.PRFR_CODE1 = BPVVCOM.VAL.FAV_DATA[1-1].FAV_CODE;
        BPRFCOM.PRFR_CODE2 = BPVVCOM.VAL.FAV_DATA[2-1].FAV_CODE;
        BPRFCOM.PRFR_CODE3 = BPVVCOM.VAL.FAV_DATA[3-1].FAV_CODE;
        BPRFCOM.PRFR_CODE4 = BPVVCOM.VAL.FAV_DATA[4-1].FAV_CODE;
        BPRFCOM.PRFR_CODE5 = BPVVCOM.VAL.FAV_DATA[5-1].FAV_CODE;
        BPRFCOM.PRFR_CODE6 = BPVVCOM.VAL.FAV_DATA[6-1].FAV_CODE;
        BPRFCOM.PRFR_CODE7 = BPVVCOM.VAL.FAV_DATA[7-1].FAV_CODE;
        BPRFCOM.PRFR_CODE8 = BPVVCOM.VAL.FAV_DATA[8-1].FAV_CODE;
        BPRFCOM.PRFR_CODE9 = BPVVCOM.VAL.FAV_DATA[9-1].FAV_CODE;
        BPRFCOM.FAV_SELECT = BPVVCOM.VAL.FAV_SELECT;
    }
    public void R000_TRANS_DATA_TO_BPVFCOM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVVCOM);
        BPVVCOM.KEY.FEE_CODE = BPRFCOM.KEY.FEE_CODE;
        BPVVCOM.KEY.REG_CODE = BPRFCOM.KEY.REG_CODE;
        BPVVCOM.KEY.CHN_NO = BPRFCOM.KEY.CHN_NO;
        BPVVCOM.KEY.FREE_FMT = BPRFCOM.KEY.FREE_FMT;
        BPVVCOM.EFF_DATE = BPRFCOM.EFF_DATE;
        BPVVCOM.EXP_DATE = BPRFCOM.EXP_DATE;
        BPVVCOM.VAL.FAV_DATA[1-1].FAV_CODE = BPRFCOM.PRFR_CODE1;
        BPVVCOM.VAL.FAV_DATA[2-1].FAV_CODE = BPRFCOM.PRFR_CODE2;
        BPVVCOM.VAL.FAV_DATA[3-1].FAV_CODE = BPRFCOM.PRFR_CODE3;
        BPVVCOM.VAL.FAV_DATA[4-1].FAV_CODE = BPRFCOM.PRFR_CODE4;
        BPVVCOM.VAL.FAV_DATA[5-1].FAV_CODE = BPRFCOM.PRFR_CODE5;
        BPVVCOM.VAL.FAV_DATA[6-1].FAV_CODE = BPRFCOM.PRFR_CODE6;
        BPVVCOM.VAL.FAV_DATA[7-1].FAV_CODE = BPRFCOM.PRFR_CODE7;
        BPVVCOM.VAL.FAV_DATA[8-1].FAV_CODE = BPRFCOM.PRFR_CODE8;
        BPVVCOM.VAL.FAV_DATA[9-1].FAV_CODE = BPRFCOM.PRFR_CODE9;
        BPVVCOM.VAL.FAV_SELECT = BPRFCOM.FAV_SELECT;
    }
    public void T000_READ_BPTFCOM() throws IOException,SQLException,Exception {
        BPTFCOM_RD = new DBParm();
        BPTFCOM_RD.TableName = "BPTFCOM";
        IBS.READ(SCCGWA, BPRFCOM, BPTFCOM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFCOM.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFCOM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_STARTBR_BPTFCOM() throws IOException,SQLException,Exception {
        BPTFCOM_BR.rp = new DBParm();
        BPTFCOM_BR.rp.TableName = "BPTFCOM";
        IBS.STARTBR(SCCGWA, BPRFCOM, BPTFCOM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFCOM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READNEXT_BPTFCOM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFCOM, this, BPTFCOM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFCOM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_BPTFCOM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFCOM_BR);
    }
    public void T000_WRITE_BPTFCOM() throws IOException,SQLException,Exception {
        BPTFCOM_RD = new DBParm();
        BPTFCOM_RD.TableName = "BPTFCOM";
        BPTFCOM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFCOM, BPTFCOM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFCOM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTFCOM_UPD() throws IOException,SQLException,Exception {
        BPTFCOM_RD = new DBParm();
        BPTFCOM_RD.TableName = "BPTFCOM";
        BPTFCOM_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCOM, BPTFCOM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FARM_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FARM_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTFCOM() throws IOException,SQLException,Exception {
        BPTFCOM_RD = new DBParm();
        BPTFCOM_RD.TableName = "BPTFCOM";
        IBS.REWRITE(SCCGWA, BPRFCOM, BPTFCOM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTFCOM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTFCOM() throws IOException,SQLException,Exception {
        BPTFCOM_RD = new DBParm();
        BPTFCOM_RD.TableName = "BPTFCOM";
        IBS.DELETE(SCCGWA, BPRFCOM, BPTFCOM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTFCOM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTFCOM.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FLG (" + SCCGWA.COMM_AREA.DBIO_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
