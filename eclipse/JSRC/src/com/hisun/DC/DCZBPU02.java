package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZBPU02 {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTBSP02_RD;
    brParm DCTBSP02_BR = new brParm();
    boolean pgmRtn = false;
    String WS_BSPNAME = " ";
    char WS_TBL_FARM_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DCRBSP02 DCRBSP02 = new DCRBSP02();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    DCRBSP02 DCRBSP0;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZBPU02 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DCRBSP0 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRBSP02);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRBSP0);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DCRBSP0, DCRBSP02);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRWBSP.INFO.FUNC == 'C') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 1**");
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 2**");
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 3**");
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 4**");
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 5**");
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'S') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 6**");
            B060_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'N') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 7**");
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'E') {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 8**");
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "**DCZBPU02,CASE 9**");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRBSP02);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DCRBSP02, DCRBSP0);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BSP();
        if (pgmRtn) return;
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BSP();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BSP();
        if (pgmRtn) return;
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        DCTBSP02_RD = new DBParm();
        DCTBSP02_RD.TableName = "DCTBSP02";
        IBS.READ(SCCGWA, DCRBSP02, DCTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        DCTBSP02_RD = new DBParm();
        DCTBSP02_RD.TableName = "DCTBSP02";
        DCTBSP02_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRBSP02, DCTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRBSP02.STATUS);
        CEP.TRC(SCCGWA, DCRBSP02.RT_CODE);
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        DCTBSP02_RD = new DBParm();
        DCTBSP02_RD.TableName = "DCTBSP02";
        DCTBSP02_RD.upd = true;
        IBS.READ(SCCGWA, DCRBSP02, DCTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRBSP02.STATUS);
        CEP.TRC(SCCGWA, DCRBSP02.RT_CODE);
        DCTBSP02_RD = new DBParm();
        DCTBSP02_RD.TableName = "DCTBSP02";
        IBS.REWRITE(SCCGWA, DCRBSP02, DCTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRBSP02.STATUS);
        CEP.TRC(SCCGWA, DCRBSP02.RT_CODE);
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        DCTBSP02_RD = new DBParm();
        DCTBSP02_RD.TableName = "DCTBSP02";
        IBS.DELETE(SCCGWA, DCRBSP02, DCTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BSP() throws IOException,SQLException,Exception {
        DCTBSP02_BR.rp = new DBParm();
        DCTBSP02_BR.rp.TableName = "DCTBSP02";
        DCTBSP02_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRBSP02, DCTBSP02_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRBSP02, this, DCTBSP02_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTBSP02_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SYS_ERR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP = ");
            CEP.TRC(SCCGWA, SCCRWBSP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
