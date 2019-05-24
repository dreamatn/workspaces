package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZMPRV {
    DBParm SOTUSR_RD;
    DBParm SOTGRP_RD;
    DBParm SOTPRIV_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORGRP SORGRP = new SORGRP();
    SORUSR SORUSR = new SORUSR();
    SORPRIV SORPRIV = new SORPRIV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP17 SOCOP17 = new SOCOP17();
    short WS_PRIV_BANK_NO = 0;
    String WS_PRIV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIPRV SOCIPRV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMPRV return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIPRV = new SOCIPRV();
        IBS.init(SCCGWA, SOCIPRV);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIPRV);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCIPRV.ACTION == 'A' 
            || SOCIPRV.ACTION == 'M') {
            if (SOCIPRV.ID.trim().length() > 0) {
                B00_READ_USER();
            }
            if (SOCIPRV.GRP_ID.trim().length() > 0) {
                B00_READ_GRP();
            }
        }
        if (SOCIPRV.IND != '0' 
            && SOCIPRV.IND != '1') {
            WS_MSGID = SOCMSG.SO_ERR_PROTECT_IND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B00_READ_USER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORUSR);
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR.KEY.ID = SOCIPRV.ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.errhdl = true;
        IBS.READ(SCCGWA, SORUSR, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_ERR_USER_ID_NO_EXIT;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B00_READ_GRP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORGRP);
        SORGRP.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORGRP.KEY.ID = SOCIPRV.GRP_ID;
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
        SOTGRP_RD.errhdl = true;
        IBS.READ(SCCGWA, SORGRP, SOTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_ERR_GROUP_ID_NO_EXIT;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCIPRV.ACTION == 'A') {
            C00_WRITE_PRV();
        } else if (SOCIPRV.ACTION == 'D') {
            C00_DELETE_PRV();
        } else if (SOCIPRV.ACTION == 'M') {
            C00_READUPD_PRV();
            C00_REWRITE_PRV();
        } else {
        }
    }
    public void C00_WRITE_PRV() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTPRIV_RD = new DBParm();
        SOTPRIV_RD.TableName = "SOTPRIV";
        SOTPRIV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORPRIV, SOTPRIV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = SOCMSG.SO_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C10_MOVE_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORPRIV);
        SORPRIV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORPRIV.KEY.ID = SOCIPRV.ID;
        SORPRIV.GRP_ID = SOCIPRV.GRP_ID;
        SORPRIV.IND = SOCIPRV.IND;
        SORPRIV.READ_IND = SOCIPRV.READ_IND;
        SORPRIV.BROW_IND = SOCIPRV.BROW_IND;
        SORPRIV.WRITE_IND = SOCIPRV.WRITE_IND;
        SORPRIV.UPD_IND = SOCIPRV.UPD_IND;
        SORPRIV.DEL_IND = SOCIPRV.DEL_IND;
        SORPRIV.CRE_USER = SORTLT.KEY.ID;
    }
    public void C00_READUPD_PRV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORPRIV);
        SORPRIV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORPRIV.KEY.ID = SOCIPRV.ID;
        SOTPRIV_RD = new DBParm();
        SOTPRIV_RD.TableName = "SOTPRIV";
        SOTPRIV_RD.upd = true;
        SOTPRIV_RD.errhdl = true;
        IBS.READ(SCCGWA, SORPRIV, SOTPRIV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_PRV() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTPRIV_RD = new DBParm();
        SOTPRIV_RD.TableName = "SOTPRIV";
        SOTPRIV_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORPRIV, SOTPRIV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_PRV() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_PRIV_BANK_NO = 0;
        else WS_PRIV_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_PRIV_ID = SOCIPRV.ID;
        SOTPRIV_RD = new DBParm();
        SOTPRIV_RD.TableName = "SOTPRIV";
        SOTPRIV_RD.where = "BANK_NO = :WS_PRIV_BANK_NO "
            + "AND ID = :WS_PRIV_ID";
        SOTPRIV_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORPRIV, this, SOTPRIV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP17);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP17.ACTION = SOCIPRV.ACTION;
        SOCOP17.ID = SOCIPRV.ID;
        SOCOP17.GRP_ID = SOCIPRV.GRP_ID;
        SOCOP17.IND = SOCIPRV.IND;
        SOCOP17.READ_IND = SOCIPRV.READ_IND;
        SOCOP17.BROW_IND = SOCIPRV.BROW_IND;
        SOCOP17.WRITE_IND = SOCIPRV.WRITE_IND;
        SOCOP17.UPD_IND = SOCIPRV.UPD_IND;
        SOCOP17.DEL_IND = SOCIPRV.DEL_IND;
        SOCOP17.CRE_USER = SORTLT.KEY.ID;
        SOCOP17.TS = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "SOP17";
        SCCFMT.DATA_PTR = SOCOP17;
        SCCFMT.DATA_LEN = 37;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
