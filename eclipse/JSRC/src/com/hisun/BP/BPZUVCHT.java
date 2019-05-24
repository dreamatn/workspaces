package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUVCHT {
    int JIBS_tmp_int;
    DBParm BPTVCHT1_RD;
    DBParm BPTVCHT2_RD;
    boolean pgmRtn = false;
    BPZUVCHT_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUVCHT_WS_TEMP_VARIABLE();
    char WS_JRN_IN_USE = ' ';
    char WS_REC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPROBL BPROBL = new BPROBL();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPCRBOBL BPCRBOBL = new BPCRBOBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRVCHT BPRVCHT = new BPRVCHT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUVCHT BPCUVCHT;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCRCWAT SCRCWAT;
    public void MP(SCCGWA SCCGWA, BPCUVCHT BPCUVCHT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUVCHT = BPCUVCHT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUVCHT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWAT = (SCRCWAT) SCCGSCA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPRVCHT);
        CEP.TRC(SCCGWA, SCRCWAT.JRN_IN_USE);
        CEP.TRC(SCCGWA, SCRCWAT.AC_DATE_AREA[1-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRCWAT.AC_DATE_AREA[2-1].AC_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_RVSNO_MIBAC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUVCHT.INPUT_DATA.AC_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_DATE_MUST_INPUT, BPCUVCHT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUVCHT.INPUT_DATA.JRN_NO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_JRNNO_CANTNOT_ZERO, BPCUVCHT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_RVSNO_MIBAC() throws IOException,SQLException,Exception {
        if ((BPCUVCHT.INPUT_DATA.AC_DATE != 0) 
            && (BPCUVCHT.INPUT_DATA.JRN_NO != 0)) {
            BPRVCHT.KEY.AC_DATE = BPCUVCHT.INPUT_DATA.AC_DATE;
            BPRVCHT.JRN_NO = BPCUVCHT.INPUT_DATA.JRN_NO;
            BPRVCHT.CRVS_NO = " ";
            BPRVCHT.CNTR_TYPE = BPCUVCHT.INPUT_DATA.CNTR_TYPE;
            BPRVCHT.PROD_CODE = BPCUVCHT.INPUT_DATA.PROD_CODE;
            BPRVCHT.EVENT_CODE = BPCUVCHT.INPUT_DATA.EVENT_CODE;
            R000_GET_VCHT_NUM();
            if (pgmRtn) return;
            T000_READ_VCHT();
            if (pgmRtn) return;
            if (WS_REC_FLG == 'Y') {
                BPCUVCHT.OUTPUT_DATA.CRVS_NO = BPRVCHT.CRVS_NO;
                BPCUVCHT.OUTPUT_DATA.MIB_AC = BPRVCHT.MIB_AC;
                BPCUVCHT.OUTPUT_DATA.BR = BPRVCHT.BR;
            }
            if (WS_REC_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCUVCHT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_VCHT_NUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWAT.AC_DATE_AREA[1-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRCWAT.AC_DATE_AREA[2-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRCWAT.JRN_IN_USE);
        WS_JRN_IN_USE = ' ';
        if (SCRCWAT.AC_DATE_AREA[1-1].AC_DATE == SCRCWAT.AC_DATE_AREA[2-1].AC_DATE) {
            WS_JRN_IN_USE = SCRCWAT.JRN_IN_USE;
        } else {
            if (SCRCWAT.JRN_IN_USE == '1') {
                WS_JRN_IN_USE = '2';
            } else {
                WS_JRN_IN_USE = '1';
            }
        }
    }
    public void T000_READ_VCHT() throws IOException,SQLException,Exception {
        BPRVCHT.KEY.SET_NO = "" + BPRVCHT.JRN_NO;
        JIBS_tmp_int = BPRVCHT.KEY.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHT.KEY.SET_NO = "0" + BPRVCHT.KEY.SET_NO;
        if (BPRVCHT.KEY.SET_NO == null) BPRVCHT.KEY.SET_NO = "";
        JIBS_tmp_int = BPRVCHT.KEY.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHT.KEY.SET_NO += " ";
        if (BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHT.KEY.PART_NO = 0;
        else BPRVCHT.KEY.PART_NO = Short.parseShort(BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
        CEP.TRC(SCCGWA, SCRCWAT.JRN_IN_USE);
        CEP.TRC(SCCGWA, WS_JRN_IN_USE);
        if (WS_JRN_IN_USE == '1') {
            BPTVCHT1_RD = new DBParm();
            BPTVCHT1_RD.TableName = "BPTVCHT1";
            BPTVCHT1_RD.where = "PART_NO = :BPRVCHT.KEY.PART_NO "
                + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHT.KEY.SET_NO "
                + "AND CRVS_NO < > :BPRVCHT.CRVS_NO "
                + "AND CNTR_TYPE = :BPRVCHT.CNTR_TYPE "
                + "AND PROD_CODE = :BPRVCHT.PROD_CODE "
                + "AND EVENT_CODE = :BPRVCHT.EVENT_CODE";
            BPTVCHT1_RD.fst = true;
            IBS.READ(SCCGWA, BPRVCHT, this, BPTVCHT1_RD);
        } else {
            BPTVCHT2_RD = new DBParm();
            BPTVCHT2_RD.TableName = "BPTVCHT2";
            BPTVCHT2_RD.where = "PART_NO = :BPRVCHT.KEY.PART_NO "
                + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :BPRVCHT.KEY.SET_NO "
                + "AND CRVS_NO < > :BPRVCHT.CRVS_NO "
                + "AND CNTR_TYPE = :BPRVCHT.CNTR_TYPE "
                + "AND PROD_CODE = :BPRVCHT.PROD_CODE "
                + "AND EVENT_CODE = :BPRVCHT.EVENT_CODE";
            BPTVCHT2_RD.fst = true;
            IBS.READ(SCCGWA, BPRVCHT, this, BPTVCHT2_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVCHT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUVCHT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUVCHT = ");
            CEP.TRC(SCCGWA, BPCUVCHT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
