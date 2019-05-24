package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBPRD {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBPRD_BR = new brParm();
    DBParm BPTBPRD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBPRD";
    String K_TBL_BPRD = "BPTBPRD ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBPRD BPRBPRD = new BPRBPRD();
    SCCGWA SCCGWA;
    BPCRBPRD BPCRBPRD;
    BPRBPRD BPRBPRD1;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRBPRD BPCRBPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBPRD = BPCRBPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBPRD1 = (BPRBPRD) BPCRBPRD.INFO.POINTER;
        IBS.init(SCCGWA, BPRBPRD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBPRD1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBPRD1, BPRBPRD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NCB050901");
        CEP.TRC(SCCGWA, BPRBPRD.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, BPRBPRD.KEY.CODE);
        if (BPCRBPRD.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'W') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'C') {
            B060_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'U') {
            B070_READ_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPRD.INFO.FUNC == 'R') {
            B080_READ_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBPRD.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBPRD);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBPRD, BPRBPRD1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PRE();
        if (pgmRtn) return;
        T000_STARTBR_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B060_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B070_READ_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_BPTBPRD();
        if (pgmRtn) return;
    }
    public void B080_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBPRD();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_PRE() throws IOException,SQLException,Exception {
        if (BPRBPRD.KEY.IBS_AC_BK.trim().length() == 0) {
            BPRBPRD.KEY.IBS_AC_BK = "%%%";
        }
        CEP.TRC(SCCGWA, BPRBPRD.KEY.CODE);
        if (BPRBPRD.KEY.CODE.trim().length() == 0) {
            BPRBPRD.KEY.CODE = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        }
    }
    public void T000_STARTBR_BPTBPRD() throws IOException,SQLException,Exception {
        BPTBPRD_BR.rp = new DBParm();
        BPTBPRD_BR.rp.TableName = "BPTBPRD";
        BPTBPRD_BR.rp.where = "IBS_AC_BK LIKE :BPRBPRD.KEY.IBS_AC_BK "
            + "AND CODE LIKE :BPRBPRD.KEY.CODE";
        IBS.STARTBR(SCCGWA, BPRBPRD, this, BPTBPRD_BR);
    }
    public void T000_READNEXT_BPTBPRD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBPRD, this, BPTBPRD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPRD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTBPRD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBPRD_BR);
    }
    public void T000_REWRITE_BPTBPRD() throws IOException,SQLException,Exception {
        BPTBPRD_RD = new DBParm();
        BPTBPRD_RD.TableName = "BPTBPRD";
        IBS.REWRITE(SCCGWA, BPRBPRD, BPTBPRD_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_DELETE_BPTBPRD() throws IOException,SQLException,Exception {
        BPTBPRD_RD = new DBParm();
        BPTBPRD_RD.TableName = "BPTBPRD";
        IBS.DELETE(SCCGWA, BPRBPRD, BPTBPRD_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_CREATE_BPTBPRD() throws IOException,SQLException,Exception {
        BPTBPRD_RD = new DBParm();
        BPTBPRD_RD.TableName = "BPTBPRD";
        IBS.WRITE(SCCGWA, BPRBPRD, BPTBPRD_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 10;
        }
    }
    public void T000_READ_UPDATE_BPTBPRD() throws IOException,SQLException,Exception {
        BPTBPRD_RD = new DBParm();
        BPTBPRD_RD.TableName = "BPTBPRD";
        BPTBPRD_RD.upd = true;
        IBS.READ(SCCGWA, BPRBPRD, BPTBPRD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPRD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_BPTBPRD() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[10-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[10-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[10-1].PARM_PTR2);
            }
            BPRBPRD = (BPRBPRD) IBS.HASHLKUP(SCCGWA, BPRBPRD.KEY.TYPE, BPRBPRD.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTBPRD_RD = new DBParm();
                BPTBPRD_RD.TableName = "BPTBPRD";
                IBS.READ(SCCGWA, BPRBPRD, BPTBPRD_RD);
            }
        } else {
            BPTBPRD_RD = new DBParm();
            BPTBPRD_RD.TableName = "BPTBPRD";
            IBS.READ(SCCGWA, BPRBPRD, BPTBPRD_RD);
        }
    } else { //FROM #ELSE
        BPTBPRD_RD = new DBParm();
        BPTBPRD_RD.TableName = "BPTBPRD";
        IBS.READ(SCCGWA, BPRBPRD, BPTBPRD_RD);
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPRD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPRD.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBPRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRBPRD = ");
            CEP.TRC(SCCGWA, BPCRBPRD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
