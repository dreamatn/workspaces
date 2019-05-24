package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.io.IOException;
import java.sql.SQLException;

public class VTZRPODR {
    DBParm VTTPODR_RD;
    int JIBS_tmp_int;
    brParm VTTPODR_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    String WS_PROD_CODE = " ";
    String WS_CNTR_TYPE = " ";
    String WS_CODE = " ";
    char WS_BILL_FLG = ' ';
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_OTH = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRPODR VTRPODR = new VTRPODR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRPODR VTCRPODR;
    VTRPODR VTRLPODR;
    public void MP(SCCGWA SCCGWA, VTCRPODR VTCRPODR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRPODR = VTCRPODR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRPODR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRPODR.RC);
        VTRLPODR = (VTRPODR) VTCRPODR.POINTER;
        IBS.init(SCCGWA, VTRPODR);
        VTCRPODR.REC_LEN = 225;
        IBS.CLONE(SCCGWA, VTRLPODR, VTRPODR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRPODR.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRPODR.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRPODR.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRPODR.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRPODR.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRPODR.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRPODR, VTRLPODR);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRPODR.OPT == 'S') {
            T000_STARTBR_VTTPODR();
        } else if (VTCRPODR.OPT == 'C') {
            T000_STARTBR_VTTPODR_FOR_CODE();
        } else if (VTCRPODR.OPT == 'W') {
            T000_STARTBR_VTTPODR_CODE();
        } else if (VTCRPODR.OPT == 'N') {
            T000_READNEXT_VTTPODR();
        } else if (VTCRPODR.OPT == 'E') {
            T000_ENDBR_VTTPODR();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        VTTPODR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTRPODR, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRPODR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        IBS.REWRITE(SCCGWA, VTRPODR, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        VTTPODR_RD.upd = true;
        IBS.READ(SCCGWA, VTRPODR, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        IBS.READ(SCCGWA, VTRPODR, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        IBS.DELETE(SCCGWA, VTRPODR, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRPODR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTPODR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPODR.CODE);
        CEP.TRC(SCCGWA, VTRPODR.KEY.PROD_CD);
        if (VTRPODR.KEY.OTH == null) VTRPODR.KEY.OTH = "";
        JIBS_tmp_int = VTRPODR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRPODR.KEY.OTH += " ";
        CEP.TRC(SCCGWA, VTRPODR.KEY.OTH.substring(21 - 1, 21 + 1 - 1));
        WS_PROD_CODE = VTRPODR.KEY.PROD_CD;
        WS_CNTR_TYPE = VTRPODR.KEY.CNTR_TYPE;
        WS_CODE = VTRPODR.CODE;
        if (VTRPODR.KEY.OTH == null) VTRPODR.KEY.OTH = "";
        JIBS_tmp_int = VTRPODR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRPODR.KEY.OTH += " ";
        WS_BILL_FLG = VTRPODR.KEY.OTH.substring(21 - 1, 21 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, WS_BILL_FLG);
        VTTPODR_BR.rp = new DBParm();
        VTTPODR_BR.rp.TableName = "VTTPODR";
        VTTPODR_BR.rp.where = "( 0 = :WS_PROD_CODE "
            + "OR PROD_CD LIKE :WS_PROD_CODE ) "
            + "AND ( ' ' = :WS_CNTR_TYPE "
            + "OR CNTR_TYPE = :WS_CNTR_TYPE ) "
            + "AND ( ' ' = :WS_CODE "
            + "OR CODE = :WS_CODE ) "
            + "AND ( ' ' = :WS_BILL_FLG "
            + "OR SUBSTR ( OTH , 21 , 1 ) = :WS_BILL_FLG )";
        VTTPODR_BR.rp.order = "CODE,EFF_DATE";
        IBS.STARTBR(SCCGWA, VTRPODR, this, VTTPODR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTPODR_FOR_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPODR.KEY.PROD_CD);
        CEP.TRC(SCCGWA, VTRPODR.KEY.BR);
        CEP.TRC(SCCGWA, VTRPODR.KEY.CCY);
        CEP.TRC(SCCGWA, VTRPODR.KEY.OTH);
        CEP.TRC(SCCGWA, VTRPODR.KEY.CNTR_TYPE);
        WS_PROD_CODE = VTRPODR.KEY.PROD_CD;
        WS_BR = VTRPODR.KEY.BR;
        WS_CCY = VTRPODR.KEY.CCY;
        WS_OTH = VTRPODR.KEY.OTH;
        WS_CNTR_TYPE = VTRPODR.KEY.CNTR_TYPE;
        VTTPODR_RD = new DBParm();
        VTTPODR_RD.TableName = "VTTPODR";
        VTTPODR_RD.where = "PROD_CD = :WS_PROD_CODE "
            + "AND BR = :WS_BR "
            + "AND ( CCY = :WS_CCY "
            + "OR CCY = '999' ) "
            + "AND CNTR_TYPE = :WS_CNTR_TYPE "
            + "AND OTH = :WS_OTH "
            + "AND STS = 'N'";
        VTTPODR_RD.fst = true;
        IBS.READ(SCCGWA, VTRPODR, this, VTTPODR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTPODR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRPODR, this, VTTPODR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTPODR_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPODR.CODE);
        WS_CODE = VTRPODR.CODE;
        VTTPODR_BR.rp = new DBParm();
        VTTPODR_BR.rp.TableName = "VTTPODR";
        VTTPODR_BR.rp.where = "CODE = :WS_CODE";
        IBS.STARTBR(SCCGWA, VTRPODR, this, VTTPODR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPODR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPODR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPODR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTPODR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTPODR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
