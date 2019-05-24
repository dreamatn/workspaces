package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCALN {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCALN_BR = new brParm();
    DBParm BPTCALN_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRCALN";
    String K_TBL_CALN = "BPTCALN ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    BPCRCALN BPCRCALN;
    BPRCALN BPRCALN1;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRCALN BPCRCALN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCALN = BPCRCALN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCALN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCALN1 = (BPRCALN) BPCRCALN.INFO.POINTER;
        IBS.init(SCCGWA, BPRCALN);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCALN1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCALN1, BPRCALN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCALN.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'W') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'C') {
            B060_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'U') {
            B070_READ_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCALN.INFO.FUNC == 'R') {
            B080_READ_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRCALN.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCALN);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCALN, BPRCALN1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PRE();
        if (pgmRtn) return;
        T000_STARTBR_BPTCALN();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTCALN();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTCALN();
        if (pgmRtn) return;
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCALN();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCALN();
        if (pgmRtn) return;
    }
    public void B060_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTCALN();
        if (pgmRtn) return;
    }
    public void B070_READ_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_BPTCALN();
        if (pgmRtn) return;
    }
    public void B080_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCALN();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_PRE() throws IOException,SQLException,Exception {
        if (BPRCALN.KEY.IBS_AC_BK.trim().length() == 0) {
            BPRCALN.KEY.IBS_AC_BK = "%%%";
        }
        CEP.TRC(SCCGWA, BPRCALN.KEY.CODE);
        if (BPRCALN.KEY.CODE.trim().length() == 0) {
            BPRCALN.KEY.CODE = "%%%%";
        }
    }
    public void T000_STARTBR_BPTCALN() throws IOException,SQLException,Exception {
        BPTCALN_BR.rp = new DBParm();
        BPTCALN_BR.rp.TableName = "BPTCALN";
        BPTCALN_BR.rp.where = "IBS_AC_BK >= :BPRCALN.KEY.IBS_AC_BK "
            + "AND CODE >= :BPRCALN.KEY.CODE "
            + "AND YEAR >= :BPRCALN.KEY.YEAR";
        IBS.STARTBR(SCCGWA, BPRCALN, this, BPTCALN_BR);
    }
    public void T000_READNEXT_BPTCALN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCALN, this, BPTCALN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCALN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCALN.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTCALN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCALN_BR);
    }
    public void T000_REWRITE_BPTCALN() throws IOException,SQLException,Exception {
        BPTCALN_RD = new DBParm();
        BPTCALN_RD.TableName = "BPTCALN";
        IBS.REWRITE(SCCGWA, BPRCALN, BPTCALN_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_DELETE_BPTCALN() throws IOException,SQLException,Exception {
        BPTCALN_RD = new DBParm();
        BPTCALN_RD.TableName = "BPTCALN";
        IBS.DELETE(SCCGWA, BPRCALN, BPTCALN_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_CREATE_BPTCALN() throws IOException,SQLException,Exception {
        BPTCALN_RD = new DBParm();
        BPTCALN_RD.TableName = "BPTCALN";
        IBS.WRITE(SCCGWA, BPRCALN, BPTCALN_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_READ_UPDATE_BPTCALN() throws IOException,SQLException,Exception {
        BPTCALN_RD = new DBParm();
        BPTCALN_RD.TableName = "BPTCALN";
        BPTCALN_RD.upd = true;
        IBS.READ(SCCGWA, BPRCALN, BPTCALN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCALN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCALN.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_BPTCALN() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[10-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[10-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[10-1].PARM_PTR2);
            }
            BPRCALN = (BPRCALN) IBS.HASHLKUP(SCCGWA, "BPRBCCY", BPRCALN.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTCALN_RD = new DBParm();
                BPTCALN_RD.TableName = "BPTCALN";
                IBS.READ(SCCGWA, BPRCALN, BPTCALN_RD);
            }
        } else {
            BPTCALN_RD = new DBParm();
            BPTCALN_RD.TableName = "BPTCALN";
            IBS.READ(SCCGWA, BPRCALN, BPTCALN_RD);
        }
    } else { //FROM #ELSE
        BPTCALN_RD = new DBParm();
        BPTCALN_RD.TableName = "BPTCALN";
        BPTCALN_RD.eqWhere = "IBS_AC_BK, CODE, YEAR";
        IBS.READ(SCCGWA, BPRCALN, BPTCALN_RD);
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCALN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCALN.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCALN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRCALN = ");
            CEP.TRC(SCCGWA, BPCRCALN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
