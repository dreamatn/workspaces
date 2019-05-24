package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTMUG {
    DBParm BPTTMUP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTMUG";
    String K_TBL_TMUP = "BPTTMUP ";
    char BPZRTMUG_FILLER1 = ' ';
    int WS_REC_COUNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTMUP BPRTMUP = new BPRTMUP();
    SCCGWA SCCGWA;
    BPCRTMUG BPCRTMUG;
    BPRTMUP BPRTMUL;
    public void MP(SCCGWA SCCGWA, BPCRTMUG BPCRTMUG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTMUG = BPCRTMUG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTMUG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTMUL = (BPRTMUP) BPCRTMUG.INFO.POINTER;
        IBS.init(SCCGWA, BPRTMUP);
        IBS.CLONE(SCCGWA, BPRTMUL, BPRTMUP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMUG.INFO.FUNC == '1'
            || BPCRTMUG.INFO.FUNC == '2'
            || BPCRTMUG.INFO.FUNC == '3'
            || BPCRTMUG.INFO.FUNC == '4'
            || BPCRTMUG.INFO.FUNC == '5') {
            B010_GROUP_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTMUG.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTMUG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_GROUP_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTMUG.INFO.FUNC == '1') {
            T000_GROUP_BPTTMUP_01();
            if (pgmRtn) return;
        } else if (BPCRTMUG.INFO.FUNC == '2') {
            T000_GROUP_BPTTMUP_02();
            if (pgmRtn) return;
        } else if (BPCRTMUG.INFO.FUNC == '3') {
            T000_GROUP_BPTTMUP_03();
            if (pgmRtn) return;
        } else if (BPCRTMUG.INFO.FUNC == '4') {
            T000_GROUP_BPTTMUP_04();
            if (pgmRtn) return;
        } else if (BPCRTMUG.INFO.FUNC == '5') {
            T000_GROUP_BPTTMUP_05();
            if (pgmRtn) return;
        } else {
        }
        BPCRTMUG.INFO.REC_COUNT = WS_REC_COUNT;
    }
    public void T000_GROUP_BPTTMUP_01() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.set = "WS-REC-COUNT=COUNT(*)";
        BPTTMUP_RD.where = "TLR_MOV_OUT = :BPRTMUP.KEY.TLR_MOV_OUT "
            + "AND STS = :BPRTMUP.STS "
            + "AND MOV_EXP_DT > :BPRTMUP.MOV_EXP_DT";
        IBS.GROUP(SCCGWA, BPRTMUP, this, BPTTMUP_RD);
    }
    public void T000_GROUP_BPTTMUP_02() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.set = "WS-REC-COUNT=COUNT(*)";
        BPTTMUP_RD.where = "TLR_MOV_IN = :BPRTMUP.KEY.TLR_MOV_IN "
            + "AND STS = :BPRTMUP.STS "
            + "AND MOV_EXP_DT > :BPRTMUP.MOV_EXP_DT";
        IBS.GROUP(SCCGWA, BPRTMUP, this, BPTTMUP_RD);
    }
    public void T000_GROUP_BPTTMUP_03() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.set = "WS-REC-COUNT=COUNT(*)";
        BPTTMUP_RD.where = "TLR_MOV_IN = :BPRTMUP.KEY.TLR_MOV_IN "
            + "AND STS = :BPRTMUP.STS "
            + "AND MOV_EXP_DT <= :BPRTMUP.MOV_EXP_DT";
        IBS.GROUP(SCCGWA, BPRTMUP, this, BPTTMUP_RD);
    }
    public void T000_GROUP_BPTTMUP_04() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.set = "WS-REC-COUNT=COUNT(*)";
        BPTTMUP_RD.where = "TLR_MOV_OUT = :BPRTMUP.KEY.TLR_MOV_OUT "
            + "AND STS = :BPRTMUP.STS "
            + "AND MOV_EXP_DT <= :BPRTMUP.MOV_EXP_DT";
        IBS.GROUP(SCCGWA, BPRTMUP, this, BPTTMUP_RD);
    }
    public void T000_GROUP_BPTTMUP_05() throws IOException,SQLException,Exception {
        BPTTMUP_RD = new DBParm();
        BPTTMUP_RD.TableName = "BPTTMUP";
        BPTTMUP_RD.set = "WS-REC-COUNT=COUNT(*)";
        BPTTMUP_RD.where = "MOV_DT = :BPRTMUP.KEY.MOV_DT";
        IBS.GROUP(SCCGWA, BPRTMUP, this, BPTTMUP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTMUG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTMUG = ");
            CEP.TRC(SCCGWA, BPCRTMUG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
