package com.hisun.CM;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZRBP31 {
    DBParm CMTBSP31_RD;
    brParm CMTBSP31_BR = new brParm();
    boolean pgmRtn = false;
    String WS_BSPNAME = " ";
    CMZRBP31_WS_BATH_PARM WS_BATH_PARM = new CMZRBP31_WS_BATH_PARM();
    char WS_TBL_FARM_FLAG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CMRBSP31 CMRBSP31 = new CMRBSP31();
    SCCGWA SCCGWA;
    SCCBATH SCCBATH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    CMRBSP31 CMRLSP31;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZRBP31 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CMRLSP31 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        IBS.init(SCCGWA, CMRBSP31);
        IBS.CLONE(SCCGWA, CMRLSP31, CMRBSP31);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
        IBS.CPY2CLS(SCCGWA, SCCBATH.PARM, WS_BATH_PARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRWBSP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'S') {
            B060_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'T') {
            B090_STARTBR1_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, CMRBSP31, CMRLSP31);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_BSP31_REC_NOT_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_BSP31_REC_NOT_EXIST, SCCRWBSP.RC);
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
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_BSP31_REC_NOT_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        CMRBSP31.KEY.AP_TYPE = WS_BATH_PARM.WS_PARM_BTYPE;
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
    public void B090_STARTBR1_RECORD_PROC() throws IOException,SQLException,Exception {
        CMRBSP31.KEY.AP_TYPE = WS_BATH_PARM.WS_PARM_BTYPE;
        T000_STARTBR1_BSP();
        if (pgmRtn) return;
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_RD = new DBParm();
        CMTBSP31_RD.TableName = "CMTBSP31";
        IBS.READ(SCCGWA, CMRBSP31, CMTBSP31_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_RD = new DBParm();
        CMTBSP31_RD.TableName = "CMTBSP31";
        CMTBSP31_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CMRBSP31, CMTBSP31_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CMTBSP31";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        CMTBSP31_RD = new DBParm();
        CMTBSP31_RD.TableName = "CMTBSP31";
        CMTBSP31_RD.upd = true;
        CMTBSP31_RD.col = "STATUS, RT_CODE, OUT_JRN, OUT_VCH_NO, OUT_DATA, TS";
        IBS.READ(SCCGWA, CMRBSP31, CMTBSP31_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_RD = new DBParm();
        CMTBSP31_RD.TableName = "CMTBSP31";
        IBS.REWRITE(SCCGWA, CMRBSP31, CMTBSP31_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_RD = new DBParm();
        CMTBSP31_RD.TableName = "CMTBSP31";
        IBS.DELETE(SCCGWA, CMRBSP31, CMTBSP31_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_BR.rp = new DBParm();
        CMTBSP31_BR.rp.TableName = "CMTBSP31";
        CMTBSP31_BR.rp.where = "AP_TYPE = :CMRBSP31.KEY.AP_TYPE";
        CMTBSP31_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CMRBSP31, this, CMTBSP31_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_STARTBR1_BSP() throws IOException,SQLException,Exception {
        CMTBSP31_BR.rp = new DBParm();
        CMTBSP31_BR.rp.TableName = "CMTBSP31";
        CMTBSP31_BR.rp.where = "AP_TYPE = :CMRBSP31.KEY.AP_TYPE";
        CMTBSP31_BR.rp.order = "AP_TYPE,AP_BATNO,BAT_NO";
        IBS.STARTBR(SCCGWA, CMRBSP31, this, CMTBSP31_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_READNEXT_BSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CMRBSP31, this, CMTBSP31_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CMTBSP31_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP=");
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
