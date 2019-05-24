package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTLBIB {
    brParm BPTCLBI_BR = new brParm();
    DBParm BPTCLBI_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTLBIB";
    String K_TBL_CLBI = "BPRCLBI";
    char WS_TBL_CLBI_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLBI BPRCLBI = new BPRCLBI();
    SCCGWA SCCGWA;
    BPRCLBI BPRCLBI1;
    BPCTLBIB BPCTLBIB;
    public void MP(SCCGWA SCCGWA, BPCTLBIB BPCTLBIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTLBIB = BPCTLBIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTLBIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCLBI1 = (BPRCLBI) BPCTLBIB.POINTER;
        IBS.init(SCCGWA, BPRCLBI);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCLBI1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCLBI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTLBIB.INFO.FUNC == '1') {
            B010_STARTBR_HOLD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '3') {
            B015_STARTBR_ALL_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '4') {
            B015_STARTBR_PVAL_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '5') {
            B015_STARTBR_ALL_BR_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '6') {
            B015_STARTBR_ALL_BR_P_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '7') {
            B070_STARTBR_BR_TOTAL();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '8') {
            B080_STARTBR_BR_TOT_PVAL();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == '9') {
            B090_STARTBR_CCY_PVAL();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == 'U') {
            B040_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == 'W') {
            B050_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTLBIB.INFO.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTLBIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCLBI, BPRCLBI1);
    }
    public void B010_STARTBR_HOLD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_HOLD();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_ALL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_ALL();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_PVAL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_PVAL();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_ALL_BR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_ALL_BR();
        if (pgmRtn) return;
    }
    public void B015_STARTBR_ALL_BR_P_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_ALL_BR_P();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B050_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTCLBI();
        if (pgmRtn) return;
    }
    public void B070_STARTBR_BR_TOTAL() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_BR_TOT();
        if (pgmRtn) return;
    }
    public void B080_STARTBR_BR_TOT_PVAL() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_BR_TOT_P();
        if (pgmRtn) return;
    }
    public void B090_STARTBR_CCY_PVAL() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTCLBI_CCY_PVAL();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTCLBI_HOLD() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.where = "PLBOX_NO = :BPRCLBI.KEY.PLBOX_NO "
            + "AND VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND CASH_TYP = :BPRCLBI.KEY.CASH_TYP "
            + "AND CCY = :BPRCLBI.KEY.CCY";
        BPTCLBI_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.where = "PLBOX_NO = :BPRCLBI.KEY.PLBOX_NO "
            + "AND VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND CASH_TYP = :BPRCLBI.KEY.CASH_TYP "
            + "AND CCY = :BPRCLBI.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_ALL() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.where = "VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND PLBOX_NO LIKE :BPRCLBI.KEY.PLBOX_NO "
            + "AND CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL >= :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_PVAL() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.where = "VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND PLBOX_NO LIKE :BPRCLBI.KEY.PLBOX_NO "
            + "AND CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL = :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_ALL_BR() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.set = "CLBI-CASH-TYP=CASH_TYP,CLBI-CCY=CCY,CLBI-PAR-VAL=PAR_VAL,CLBI-M-FLG=M_FLG,CLBI-GD-NUM=SUM(GD_NUM),CLBI-BD-NUM=SUM(BD_NUM),CLBI-HBD-NUM=SUM(HBD_NUM)";
        BPTCLBI_BR.rp.where = "CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL >= :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.grp = "CASH_TYP,CCY,PAR_VAL,M_FLG";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_ALL_BR_P() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.set = "CLBI-CASH-TYP=CASH_TYP,CLBI-CCY=CCY,CLBI-PAR-VAL=PAR_VAL,CLBI-M-FLG=M_FLG,CLBI-GD-NUM=SUM(GD_NUM),CLBI-BD-NUM=SUM(BD_NUM),CLBI-HBD-NUM=SUM(HBD_NUM)";
        BPTCLBI_BR.rp.where = "CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL = :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.grp = "CASH_TYP,CCY,PAR_VAL,M_FLG";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_BR_TOT() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.set = "CLBI-CASH-TYP=CASH_TYP,CLBI-CCY=CCY,CLBI-PAR-VAL=PAR_VAL,CLBI-M-FLG=M_FLG,CLBI-GD-NUM=SUM(GD_NUM),CLBI-BD-NUM=SUM(BD_NUM),CLBI-HBD-NUM=SUM(HBD_NUM)";
        BPTCLBI_BR.rp.where = "VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL >= :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.grp = "CASH_TYP,CCY,PAR_VAL,M_FLG";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_BR_TOT_P() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.set = "CLBI-CASH-TYP=CASH_TYP,CLBI-CCY=CCY,CLBI-PAR-VAL=PAR_VAL,CLBI-M-FLG=M_FLG,CLBI-GD-NUM=SUM(GD_NUM),CLBI-BD-NUM=SUM(BD_NUM),CLBI-HBD-NUM=SUM(HBD_NUM)";
        BPTCLBI_BR.rp.where = "VB_BR = :BPRCLBI.KEY.VB_BR "
            + "AND CCY LIKE :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL = :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG LIKE :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        BPTCLBI_BR.rp.grp = "CASH_TYP,CCY,PAR_VAL,M_FLG";
        BPTCLBI_BR.rp.order = "CCY,M_FLG,PAR_VAL DESC,CASH_TYP";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_STARTBR_BPTCLBI_CCY_PVAL() throws IOException,SQLException,Exception {
        BPTCLBI_BR.rp = new DBParm();
        BPTCLBI_BR.rp.TableName = "BPTCLBI";
        BPTCLBI_BR.rp.where = "CCY = :BPRCLBI.KEY.CCY "
            + "AND PAR_VAL = :BPRCLBI.KEY.PAR_VAL "
            + "AND M_FLG = :BPRCLBI.KEY.M_FLG "
            + "AND ( GD_NUM > 0 "
            + "OR BD_NUM > 0 "
            + "OR HBD_NUM > 0 )";
        IBS.STARTBR(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
    }
    public void T000_READNEXT_BPTCLBI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCLBI, this, BPTCLBI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTLBIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTLBIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTCLBI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLBI_BR);
    }
    public void T000_REWRITE_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        IBS.REWRITE(SCCGWA, BPRCLBI, BPTCLBI_RD);
    }
    public void T000_READUPD_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        BPTCLBI_RD.upd = true;
        IBS.READ(SCCGWA, BPRCLBI, BPTCLBI_RD);
    }
    public void T000_DELETE_BPTCLBI() throws IOException,SQLException,Exception {
        BPTCLBI_RD = new DBParm();
        BPTCLBI_RD.TableName = "BPTCLBI";
        IBS.DELETE(SCCGWA, BPRCLBI, BPTCLBI_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTLBIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTLBIB = ");
            CEP.TRC(SCCGWA, BPCTLBIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
