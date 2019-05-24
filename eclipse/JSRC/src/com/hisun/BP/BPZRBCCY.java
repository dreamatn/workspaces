package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBCCY {
    brParm BPTBCCY_BR = new brParm();
    DBParm BPTBCCY_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBCCY";
    String K_TBL_BCCY = "BPTBCCY ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCRBCCY BPCRBCCY;
    BPRBCCY BPRBCCY;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRBCCY BPCRBCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBCCY = BPCRBCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBCCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBCCY = (BPRBCCY) BPCRBCCY.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRBCCY.INFO.OP_FUNC);
        if (BPCRBCCY.INFO.OP_FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'A') {
            B060_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'M') {
            B070_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCCY.INFO.OP_FUNC == 'U') {
            B080_READ_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBCCY.INFO.OP_FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PRE();
        if (pgmRtn) return;
        T000_STARTBR_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B060_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ADD_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B070_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBCCY();
        if (pgmRtn) return;
    }
    public void B080_READ_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_BPTBCCY();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_PRE() throws IOException,SQLException,Exception {
        if (BPRBCCY.KEY.IBS_AC_BK.trim().length() == 0) {
            BPRBCCY.KEY.IBS_AC_BK = "%%%";
        }
        CEP.TRC(SCCGWA, BPRBCCY.KEY.CD);
        if (BPRBCCY.KEY.CD.trim().length() == 0) {
            BPRBCCY.KEY.CD = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        }
    }
    public void T000_STARTBR_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_BR.rp = new DBParm();
        BPTBCCY_BR.rp.TableName = "BPTBCCY";
        BPTBCCY_BR.rp.eqWhere = "IBS_AC_BK";
        BPTBCCY_BR.rp.where = "CD >= :BPRBCCY.KEY.CD "
            + "AND IBS_AC_BK >= :BPRBCCY.KEY.IBS_AC_BK";
        BPTBCCY_BR.rp.order = "IBS_AC_BK, CD DESC";
        IBS.STARTBR(SCCGWA, BPRBCCY, this, BPTBCCY_BR);
        CEP.TRC(SCCGWA, BPRBCCY.KEY.CD);
    }
    public void T000_READNEXT_BPTBCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBCCY, this, BPTBCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCCY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCCY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTBCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBCCY_BR);
    }
    public void T000_DELETE_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        IBS.DELETE(SCCGWA, BPRBCCY, BPTBCCY_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_ADD_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        IBS.WRITE(SCCGWA, BPRBCCY, BPTBCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_REWRITE_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        IBS.REWRITE(SCCGWA, BPRBCCY, BPTBCCY_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_READ_UPDATE_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        BPTBCCY_RD.upd = true;
        IBS.READ(SCCGWA, BPRBCCY, BPTBCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCCY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCCY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_QUERY_BPTBCCY() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[10-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[10-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[10-1].PARM_PTR2);
            }
            BPRBCCY = (BPRBCCY) IBS.HASHLKUP(SCCGWA, "BPRBCCY", BPRBCCY.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTBCCY_RD = new DBParm();
                BPTBCCY_RD.TableName = "BPTBCCY";
                IBS.READ(SCCGWA, BPRBCCY, BPTBCCY_RD);
            }
        } else {
            BPTBCCY_RD = new DBParm();
            BPTBCCY_RD.TableName = "BPTBCCY";
            IBS.READ(SCCGWA, BPRBCCY, BPTBCCY_RD);
        }
    } else { //FROM #ELSE
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        IBS.READ(SCCGWA, BPRBCCY, BPTBCCY_RD);
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCCY.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCCY.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBCCY.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCRBCCY= ");
            CEP.TRC(SCCGWA, BPCRBCCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
