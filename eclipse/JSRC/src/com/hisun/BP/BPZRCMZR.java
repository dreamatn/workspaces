package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRCMZR {
    DBParm BPTCMZR_RD;
    brParm BPTCMZR_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRCMZR";
    String K_TBL_FARM = "BPTCMZR";
    int K_EFF_STRT = 00000000;
    int K_EFF_ENDT = 99999999;
    String WS_TEMP_RECORD = " ";
    int WS_EFF_STRT = 0;
    int WS_EFF_ENDT = 0;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCMZR BPRCMZR = new BPRCMZR();
    SCCGWA SCCGWA;
    BPCRCMZR BPCRCMZR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRCMZR BPRCMZR1;
    public void MP(SCCGWA SCCGWA, BPCRCMZR BPCRCMZR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCMZR = BPCRCMZR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRCMZR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCMZR1 = (BPRCMZR) BPCRCMZR.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCMZR);
        BPCRCMZR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRCMZR.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRCMZR1, BPRCMZR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRCMZR.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCMZR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRCMZR, BPRCMZR1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTCMZR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "WRITE DONE");
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTCMZR_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVZHJ");
        if (BPCRCMZR.INFO.OPT == 'M') {
            T000_READ_BPTCMZR();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'B') {
            T000_READ_BPTCMZR_BIZ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
            CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_BIZ);
            CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
            CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
            CEP.TRC(SCCGWA, "AAA");
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTCMZR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRCMZR.INFO.OPT == 'S') {
            T000_STARTBR_BPTCMZR();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'T') {
            T000_STARTBR_BPTCMZR_1();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'Z') {
            T000_STARTBR_BPTCMZR_2();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'O') {
            T000_STARTBR_BPTCMZR_4();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'N') {
            T000_READNEXT_BPTCMZR();
            if (pgmRtn) return;
        } else if (BPCRCMZR.INFO.OPT == 'E') {
            T000_ENDBR_BPTCMZR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRCMZR.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        IBS.DELETE(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCMZR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTCMZR() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        IBS.READ(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "222");
            BPCRCMZR.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "333");
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "444");
        }
    }
    public void T000_READ_BPTCMZR_BIZ() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        IBS.READ(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "222");
            BPCRCMZR.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "333");
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "444");
        }
    }
    public void T000_STARTBR_BPTCMZR() throws IOException,SQLException,Exception {
        if (BPRCMZR.KEY.CMZ_FLG == '1') {
            CEP.TRC(SCCGWA, "AAA");
            BPTCMZR_BR.rp = new DBParm();
            BPTCMZR_BR.rp.TableName = "BPTCMZR";
            BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                + "AND CI_NO = :BPRCMZR.KEY.CI_NO "
                + "AND EFF_DATE >= :BPRCMZR.KEY.EFF_DATE";
            BPTCMZR_BR.rp.order = "CI_NO";
            IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
        } else if ((BPRCMZR.KEY.CMZ_FLG == '2' 
                || BPRCMZR.KEY.CMZ_FLG == '3')) {
            CEP.TRC(SCCGWA, "BBB");
            BPTCMZR_BR.rp = new DBParm();
            BPTCMZR_BR.rp.TableName = "BPTCMZR";
            BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                + "AND CMZ_AC = :BPRCMZR.KEY.CMZ_AC "
                + "AND EFF_DATE >= :BPRCMZR.KEY.EFF_DATE";
            BPTCMZR_BR.rp.order = "CMZ_AC";
            IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
        } else if (BPRCMZR.KEY.CMZ_FLG == '4') {
            CEP.TRC(SCCGWA, "CCC");
            BPTCMZR_BR.rp = new DBParm();
            BPTCMZR_BR.rp.TableName = "BPTCMZR";
            BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                + "AND CMZ_BIZ = :BPRCMZR.KEY.CMZ_BIZ "
                + "AND EFF_DATE >= :BPRCMZR.KEY.EFF_DATE";
            BPTCMZR_BR.rp.order = "CMZ_BIZ";
            IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                BPCRCMZR.RETURN_INFO = 'F';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPCRCMZR.RETURN_INFO = 'N';
            } else {
            }
    }
    public void T000_STARTBR_BPTCMZR_4() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("100100")) {
            if (BPRCMZR.KEY.CMZ_FLG == '1') {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CI_NO = :BPRCMZR.KEY.CI_NO "
                    + "AND EFF_DATE = :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CI_NO";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            } else if ((BPRCMZR.KEY.CMZ_FLG == '2' 
                    || BPRCMZR.KEY.CMZ_FLG == '3')) {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CMZ_AC = :BPRCMZR.KEY.CMZ_AC "
                    + "AND EFF_DATE = :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CMZ_AC";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            } else if (BPRCMZR.KEY.CMZ_FLG == '4') {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CMZ_BIZ = :BPRCMZR.KEY.CMZ_BIZ "
                    + "AND EFF_DATE = :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CMZ_BIZ";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            }
        } else {
            if (BPRCMZR.KEY.CMZ_FLG == '1') {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CI_NO = :BPRCMZR.KEY.CI_NO "
                    + "AND EFF_DATE <= :BPRCMZR.KEY.EFF_DATE "
                    + "AND EXP_DATE >= :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CI_NO";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            } else if ((BPRCMZR.KEY.CMZ_FLG == '2' 
                    || BPRCMZR.KEY.CMZ_FLG == '3')) {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CMZ_AC = :BPRCMZR.KEY.CMZ_AC "
                    + "AND EFF_DATE <= :BPRCMZR.KEY.EFF_DATE "
                    + "AND EXP_DATE >= :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CMZ_AC";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            } else if (BPRCMZR.KEY.CMZ_FLG == '4') {
                BPTCMZR_BR.rp = new DBParm();
                BPTCMZR_BR.rp.TableName = "BPTCMZR";
                BPTCMZR_BR.rp.where = "CMZ_FLG = :BPRCMZR.KEY.CMZ_FLG "
                    + "AND CMZ_BIZ = :BPRCMZR.KEY.CMZ_BIZ "
                    + "AND EFF_DATE <= :BPRCMZR.KEY.EFF_DATE "
                    + "AND EXP_DATE >= :BPRCMZR.KEY.EFF_DATE";
                BPTCMZR_BR.rp.order = "CMZ_BIZ";
                IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTCMZR_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        BPTCMZR_BR.rp = new DBParm();
        BPTCMZR_BR.rp.TableName = "BPTCMZR";
        BPTCMZR_BR.rp.where = "CI_NO = :BPRCMZR.KEY.CI_NO";
        IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAA1");
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBB1");
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
            CEP.TRC(SCCGWA, "CCC1");
        }
    }
    public void T000_STARTBR_BPTCMZR_2() throws IOException,SQLException,Exception {
        BPTCMZR_BR.rp = new DBParm();
        BPTCMZR_BR.rp.TableName = "BPTCMZR";
        BPTCMZR_BR.rp.where = "CMZ_BIZ = :BPRCMZR.KEY.CMZ_BIZ";
        IBS.STARTBR(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTCMZR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTCMZR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMZR_BR);
    }
    public void T000_WRITE_BPTCMZR() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        BPTCMZR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, "WRITE NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCMZR.RETURN_INFO = 'D';
            CEP.TRC(SCCGWA, "WRITE DUPKEY");
        } else {
            CEP.TRC(SCCGWA, "WRITE OTHER");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTCMZR_UPD() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        BPTCMZR_RD.upd = true;
        IBS.READ(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRCMZR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTCMZR() throws IOException,SQLException,Exception {
        BPTCMZR_RD = new DBParm();
        BPTCMZR_RD.TableName = "BPTCMZR";
        IBS.REWRITE(SCCGWA, BPRCMZR, BPTCMZR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRCMZR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRCMZR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCMZR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCMZR = ");
            CEP.TRC(SCCGWA, BPCRCMZR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
