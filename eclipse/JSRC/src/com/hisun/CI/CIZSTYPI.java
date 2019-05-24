package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSTYPI {
    int JIBS_tmp_int;
    DBParm CITTYP_RD;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    char WS_MSREL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CIRTYP CIRTYP = new CIRTYP();
    SCCGWA SCCGWA;
    CICTYPI CICTYPI;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, CICTYPI CICTYPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICTYPI = CICTYPI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSTYPI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CICTYPI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (CICTYPI.FUNC == '0') {
            B210_ADD_CITTYP_INFO();
        } else if (CICTYPI.FUNC == '1') {
            B220_UPD_CITTYP_INFO();
        } else if (CICTYPI.FUNC == '2') {
            B230_DEL_CITTYP_INFO();
        } else if (CICTYPI.FUNC == '3') {
            B240_QRY_CITTYP_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CICTYPI.TYPE == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void B210_ADD_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = "" + CICTYPI.TYPE;
        JIBS_tmp_int = CIRTYP.KEY.TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRTYP.KEY.TYPE = "0" + CIRTYP.KEY.TYPE;
        CIRTYP.NAME = CICTYPI.NAME;
        CIRTYP.OBJ_TYP = CICTYPI.OBJ_TYP;
        CIRTYP.SPE_FLG = CICTYPI.SPE_FLG;
        CIRTYP.SPE_ACT_FLG = CICTYPI.SPE_ACT_FLG;
        CIRTYP.STA_TM = CICTYPI.STA_TM;
        CIRTYP.END_TM = CICTYPI.END_TM;
        T000_INSERT_CITTYP();
    }
    public void B220_UPD_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = "" + CICTYPI.TYPE;
        JIBS_tmp_int = CIRTYP.KEY.TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRTYP.KEY.TYPE = "0" + CIRTYP.KEY.TYPE;
        T000_READUPD_CITTYP();
        CIRTYP.NAME = CICTYPI.NAME;
        CIRTYP.OBJ_TYP = CICTYPI.OBJ_TYP;
        CIRTYP.SPE_FLG = CICTYPI.SPE_FLG;
        CIRTYP.SPE_ACT_FLG = CICTYPI.SPE_ACT_FLG;
        CIRTYP.STA_TM = CICTYPI.STA_TM;
        CIRTYP.END_TM = CICTYPI.END_TM;
        T000_REWRITE_CITTYP();
    }
    public void B230_DEL_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = "" + CICTYPI.TYPE;
        JIBS_tmp_int = CIRTYP.KEY.TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRTYP.KEY.TYPE = "0" + CIRTYP.KEY.TYPE;
        T000_READUPD_CITTYP();
        T000_DELETE_CITTYP();
    }
    public void B240_QRY_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = "" + CICTYPI.TYPE;
        JIBS_tmp_int = CIRTYP.KEY.TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRTYP.KEY.TYPE = "0" + CIRTYP.KEY.TYPE;
        T000_READ_CITTYP();
    }
    public void T000_READ_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_READUPD_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        CITTYP_RD.upd = true;
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_REWRITE_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.REWRITE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_INSERT_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.WRITE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_DELETE_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.DELETE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
