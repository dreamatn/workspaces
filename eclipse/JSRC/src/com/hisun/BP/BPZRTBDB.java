package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBDB {
    brParm BPTTBVD_BR = new brParm();
    DBParm BPTTBVD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTBDB";
    String K_TBL_TBVD = "BPTTBVD ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    BPCRTBDB BPCRTBDB;
    BPRTBVD BPRTBVL;
    public void MP(SCCGWA SCCGWA, BPCRTBDB BPCRTBDB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBDB = BPCRTBDB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTBDB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTBVL = (BPRTBVD) BPCRTBDB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTBVD);
        IBS.CLONE(SCCGWA, BPRTBVL, BPRTBVD);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPRTBVD.NOW_NO);
        CEP.TRC(SCCGWA, BPRTBVD.NUM);
        CEP.TRC(SCCGWA, BPRTBVD.TYPE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBDB.INFO.FUNC == '1'
            || BPCRTBDB.INFO.FUNC == '2'
            || BPCRTBDB.INFO.FUNC == '3'
            || BPCRTBDB.INFO.FUNC == '4'
            || BPCRTBDB.INFO.FUNC == '5'
            || BPCRTBDB.INFO.FUNC == '6'
            || BPCRTBDB.INFO.FUNC == '7'
            || BPCRTBDB.INFO.FUNC == '8'
            || BPCRTBDB.INFO.FUNC == '9'
            || BPCRTBDB.INFO.FUNC == 'S'
            || BPCRTBDB.INFO.FUNC == 'A'
            || BPCRTBDB.INFO.FUNC == 'B'
            || BPCRTBDB.INFO.FUNC == 'G'
            || BPCRTBDB.INFO.FUNC == 'H'
            || BPCRTBDB.INFO.FUNC == 'I'
            || BPCRTBDB.INFO.FUNC == 'R'
            || BPCRTBDB.INFO.FUNC == 'J'
            || BPCRTBDB.INFO.FUNC == 'L'
            || BPCRTBDB.INFO.FUNC == 'P'
            || BPCRTBDB.INFO.FUNC == 'K'
            || BPCRTBDB.INFO.FUNC == 'O') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'N'
            || BPCRTBDB.INFO.FUNC == 'M') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'E'
            || BPCRTBDB.INFO.FUNC == 'F') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'W') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'C') {
            B060_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTBDB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTBDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTBVD, BPRTBVL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBDB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTBVD_01();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '2') {
            T000_STARTBR_BPTTBVD_02();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '3') {
            T000_STARTBR_BPTTBVD_03();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '4') {
            T000_STARTBR_BPTTBVD_04();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '5') {
            T000_STARTBR_BPTTBVD_05();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '6') {
            T000_STARTBR_BPTTBVD_06();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '7') {
            T000_STARTBR_BPTTBVD_07();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '8') {
            T000_STARTBR_BPTTBVD_08();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == '9') {
            T000_STARTBR_BPTTBVD_09();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'S') {
            T000_STARTBR_BPTTBVD_10();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'A') {
            T000_STARTBR_BPTTBVD_11();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'B') {
            T000_STARTBR_BPTTBVD_12();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'G') {
            T000_STARTBR_BPTTBVD_13();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'H') {
            T000_STARTBR_BPTTBVD_14();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'I') {
            T000_STARTBR_BPTTBVD_15();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'R') {
            T000_STARTBR_BPTTBVD_16();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'J') {
            T000_STARTBR_BPTTBVD_17();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'L') {
            T000_STARTBR_BPTTBVD_18();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'P') {
            T000_STARTBR_BPTTBVD_19();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'O') {
            T000_STARTBR_BPTTBVD_26();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() > 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() > 0 
                && BPRTBVD.KEY.STS != ' ') {
            T000_STARTBR_BPTTBVD_24();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() > 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() == 0 
                && BPRTBVD.KEY.STS != ' ') {
            T000_STARTBR_BPTTBVD_25();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() == 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() == 0) {
            T000_STARTBR_BPTTBVD_20();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() > 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() == 0) {
            T000_STARTBR_BPTTBVD_21();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() == 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() > 0) {
            T000_STARTBR_BPTTBVD_22();
            if (pgmRtn) return;
        } else if (BPRTBVD.KEY.PL_BOX_NO.trim().length() > 0 
                && BPRTBVD.KEY.BV_CODE.trim().length() > 0) {
            T000_STARTBR_BPTTBVD_23();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBDB.INFO.FUNC == 'N') {
            T000_READNEXT_BPTTBVD();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'M') {
            T000_READNEXT_BPTTBVD_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBDB.INFO.FUNC == 'E') {
            CEP.TRC(SCCGWA, "RTBDB-ENDBR-01");
            T000_ENDBR_BPTTBVD();
            if (pgmRtn) return;
        } else if (BPCRTBDB.INFO.FUNC == 'F') {
            T000_ENDBR_BPTTBVD_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void B060_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTTBVD();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTBVD_01() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_02() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "BEG_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_03() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND END_NO >= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= :BPRTBVD.BEG_NO "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_04() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND BEG_NO = :BPRTBVD.BEG_NO "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_05() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 2;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_06() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE "
            + "AND STS IN ( '0' , '1' )";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_07() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "BEG_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_08() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND END_NO >= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= :BPRTBVD.BEG_NO "
            + "AND STS = :BPRTBVD.KEY.STS";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_09() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS IN ( '0' , '1' )";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_10() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS IN ( '0' , '1' ) "
            + "AND TYPE LIKE :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_11() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,VALUE,HEAD_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_12() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,VALUE,HEAD_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_13() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS LIKE :BPRTBVD.KEY.STS "
            + "AND TYPE LIKE :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_14() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE "
            + "AND STS IN ( '0' , '1' )";
        BPTTBVD_BR.rp.grp = "BV_CODE , STS ,HEAD_NO";
        BPTTBVD_BR.rp.set = "TBVD-NUM=SUM(NUM)";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_15() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE "
            + "AND STS IN ( '0' , '1' )";
        BPTTBVD_BR.rp.grp = "BV_CODE , VALUE";
        BPTTBVD_BR.rp.set = "TBVD-NUM=SUM(NUM)";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_16() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 2;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_17() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_18() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE IN ( '0' , '1' ) "
            + "AND STS IN ( '0' , '1' )";
        BPTTBVD_BR.rp.grp = "BV_CODE , STS";
        BPTTBVD_BR.rp.set = "TBVD-NUM=SUM(NUM)";
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_19() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS IN ( '0' , '1' ) "
            + "AND TYPE LIKE :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 2;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_20() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_21() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_22() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_23() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_24() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND STS = :BPRTBVD.KEY.STS "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_25() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS = :BPRTBVD.KEY.STS "
            + "AND TYPE = :BPRTBVD.TYPE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_STARTBR_BPTTBVD_26() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS LIKE :BPRTBVD.KEY.STS "
            + "AND TYPE LIKE :BPRTBVD.TYPE "
            + "AND BV_CODE LIKE :BPRTBVD.KEY.BV_CODE";
        BPTTBVD_BR.rp.Reqid = 1;
        BPTTBVD_BR.rp.upd = true;
        BPTTBVD_BR.rp.order = "PL_BOX_NO,BV_CODE,STS,HEAD_NO,END_NO";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_READNEXT_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBDB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBDB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTTBVD_02() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTBDB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTBDB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, BPTTBVD_BR);
    }
    public void T000_ENDBR_BPTTBVD_02() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, BPTTBVD_BR);
    }
    public void T000_REWRITE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.Reqid = 1;
        IBS.REWRITE(SCCGWA, BPRTBVD, BPTTBVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_DELETE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.Reqid = 1;
        IBS.DELETE(SCCGWA, BPRTBVD, BPTTBVD_RD);
    }
    public void T000_CREATE_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        IBS.WRITE(SCCGWA, BPRTBVD, BPTTBVD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTBDB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTBDB = ");
            CEP.TRC(SCCGWA, BPCRTBDB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
