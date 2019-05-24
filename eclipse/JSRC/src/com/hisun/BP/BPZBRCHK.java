package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBRCHK {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCLIB_BR = new brParm();
    brParm BPTVHPB_BR = new brParm();
    DBParm BPTTBVD_RD;
    brParm BPTCMOV_BR = new brParm();
    brParm BPTDMOV_BR = new brParm();
    brParm BPTBMOV_BR = new brParm();
    brParm BPTALIB_BR = new brParm();
    brParm BPTAPLI_BR = new brParm();
    brParm BPTTLVB_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_I = 0;
    BPZBRCHK_WS_DATE WS_DATE = new BPZBRCHK_WS_DATE();
    BPZBRCHK_WS_OUTPUT WS_OUTPUT = new BPZBRCHK_WS_OUTPUT();
    String[] WS_CHK_MSG = new String[20];
    int WS_SUPR_BR_OLD = 0;
    int WS_TR_BRANCH = 0;
    char WS_ATTR_OLD = ' ';
    String WS_ERR_MSG = " ";
    int WS_BR_OLD_BBR = 0;
    int WS_AC_MSTBR = 0;
    int WS_TEMP_BBR = 0;
    int WS_AC_MSTBR_GEN = 0;
    char TBVD_RETURN_INFO = ' ';
    char BMOV_RETURN_INFO = ' ';
    char CMOV_RETURN_INFO = ' ';
    char DMOV_RETURN_INFO = ' ';
    char APLI_RETURN_INFO = ' ';
    char ALIB_RETURN_INFO = ' ';
    char VHPB_RETURN_INFO = ' ';
    char TLVB_RETURN_INFO = ' ';
    char CLIB_RETURN_INFO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRDMOV BPRDMOV = new BPRDMOV();
    BPRALIB BPRALIB = new BPRALIB();
    BPRAPLI BPRAPLI = new BPRAPLI();
    AICSCVBR AICSCVBR = new AICSCVBR();
    AICPGINF AICPGINF = new AICPGINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCBRCHK BPCBRCHK;
    public void MP(SCCGWA SCCGWA, BPCBRCHK BPCBRCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBRCHK = BPCBRCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBRCHK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_BEFORE_CHG_BR();
        if (pgmRtn) return;
        R000_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (WS_CHK_MSG[WS_I-1].trim().length() > 0) {
                WS_OUTPUT.WS_OLD_BR = BPCBRCHK.OLD_BR;
                WS_OUTPUT.WS_NEW_BR = BPCBRCHK.NEW_BR;
                WS_OUTPUT.WS_OUT_RMK = WS_CHK_MSG[WS_I-1];
                B020_OUTPUT_NOT_ALLOW_FMT();
                if (pgmRtn) return;
            }
        }
        Z_RET();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 264;
        SCCMPAG.SCR_COL_CNT = 264;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_BEFORE_CHG_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.OLD_BR = BPCBRCHK.OLD_BR;
        BPCUCHBR.ORGI_FLG = '0';
        S000_CALL_BPZUCHBR();
        if (pgmRtn) return;
        if (BPCUCHBR.NEW_BR != 0 
            && BPCUCHBR.NEW_BR > BPCBRCHK.NEW_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHE_BR_NOT_EXIST);
        }
        if (BPCUCHBR.INCO_DATE != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCBRCHK.OLD_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_SUPR_BR_OLD = BPCPQORG.SUPR_BR;
            WS_ATTR_OLD = BPCPQORG.ATTR;
            WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, WS_SUPR_BR_OLD);
            CEP.TRC(SCCGWA, WS_TR_BRANCH);
            if (WS_ATTR_OLD == '3') {
                if (WS_TR_BRANCH == BPCBRCHK.OLD_BR 
                    || WS_TR_BRANCH == WS_SUPR_BR_OLD) {
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
                }
            } else {
                if (WS_TR_BRANCH == BPCBRCHK.OLD_BR) {
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
                }
            }
            B005_01_CASHCK_USER();
            if (pgmRtn) return;
            B005_02_BVCK_USER();
            if (pgmRtn) return;
            B005_03_CASHCK_BR();
            if (pgmRtn) return;
            B005_04_BVCK_BR();
            if (pgmRtn) return;
            B005_05_CASHCK_APPSTS();
            if (pgmRtn) return;
            B005_06_BVCK_APPSTS();
            if (pgmRtn) return;
            B005_07_CVBR_APLI();
            if (pgmRtn) return;
            B005_08_AIZPGINF();
            if (pgmRtn) return;
        }
    }
    public void B005_01_CASHCK_USER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_TLVB();
        if (pgmRtn) return;
        T000_READNEXT_TLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TLVB_RETURN_INFO);
        if (TLVB_RETURN_INFO == 'F') {
            WS_CHK_MSG[1-1] = "机构撤并不能有现金箱库，�?删除";
        }
        T000_ENDBR_BPTTLVB();
        if (pgmRtn) return;
    }
    public void B005_02_BVCK_USER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTVHPB();
        if (pgmRtn) return;
        T000_READNEXT_BPTVHPB();
        if (pgmRtn) return;
        while (VHPB_RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_READ_BPTTBVD_FIRST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_CHK_MSG[2-1] = "迁出机构凭证未全部上�?";
            }
            T000_READNEXT_BPTVHPB();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTVHPB();
        if (pgmRtn) return;
    }
    public void B005_03_CASHCK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTCMOV();
        if (pgmRtn) return;
        T000_READNEXT_BPTCMOV();
        if (pgmRtn) return;
        while (CMOV_RETURN_INFO != 'N') {
            if (CMOV_RETURN_INFO == 'F') {
                WS_CHK_MSG[3-1] = "迁出机构存在现金在�??";
            }
            T000_READNEXT_BPTCMOV();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B005_04_BVCK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTBMOV();
        if (pgmRtn) return;
        T000_READNEXT_BPTBMOV();
        if (pgmRtn) return;
        while (BMOV_RETURN_INFO != 'N') {
            if (BMOV_RETURN_INFO == 'F') {
                WS_CHK_MSG[4-1] = "迁出机构存在凭证在�??";
            }
            T000_READNEXT_BPTBMOV();
            if (pgmRtn) return;
        }
        T000_ENDBR_BMOV();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRDMOV);
        BPRDMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRDMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTDMOV();
        if (pgmRtn) return;
        T000_READNEXT_BPTDMOV();
        if (pgmRtn) return;
        while (DMOV_RETURN_INFO != 'N') {
            if (DMOV_RETURN_INFO == 'F') {
                WS_CHK_MSG[5-1] = "迁出机构存在吞卡在�??";
            }
            T000_READNEXT_BPTDMOV();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTDMOV();
        if (pgmRtn) return;
    }
    public void B005_05_CASHCK_APPSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRALIB);
        BPRALIB.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRALIB.UP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTALIB();
        if (pgmRtn) return;
        T000_READNEXT_BPTALIB();
        if (pgmRtn) return;
        while (ALIB_RETURN_INFO != 'N') {
            WS_CHK_MSG[6-1] = "迁出机构存在处于审批状�?�的出入库现�?";
            ALIB_RETURN_INFO = 'N';
        }
        T000_ENDBR_BPTALIB();
        if (pgmRtn) return;
    }
    public void B005_06_BVCK_APPSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        BPRAPLI.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRAPLI.UP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_STARTBR_BPTAPLI();
        if (pgmRtn) return;
        T000_READNEXT_BPTAPLI();
        if (pgmRtn) return;
        while (APLI_RETURN_INFO != 'N') {
            WS_CHK_MSG[7-1] = "迁出机构存在处于审批状�?�的出入库凭�?";
            APLI_RETURN_INFO = 'N';
        }
        T000_ENDBR_BPTAPLI();
        if (pgmRtn) return;
    }
    public void B005_07_CVBR_APLI() throws IOException,SQLException,Exception {
        WS_AC_MSTBR = BPCUCHBR.OLD_BR;
        B023_GET_CHG_BR_BBR();
        if (pgmRtn) return;
        WS_BR_OLD_BBR = WS_AC_MSTBR_GEN;
        if (WS_BR_OLD_BBR == BPCUCHBR.OLD_BR) {
            IBS.init(SCCGWA, AICSCVBR);
            AICSCVBR.FUNC = 'K';
            AICSCVBR.OBR = BPCUCHBR.OLD_BR;
            S000_CALL_AIZSCVBR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "POCGGL0401001");
            CEP.TRC(SCCGWA, AICSCVBR.RC.RC_CODE);
            if (AICSCVBR.RC.RC_CODE != 0) {
                WS_CHK_MSG[8-1] = "迁出机构内部户在新机构缺少存在映射关�?";
            }
        }
    }
    public void B005_08_AIZPGINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-GRVS-EXP", AICPGINF, true);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPGINF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("AI3801")) {
            WS_CHK_MSG[9-1] = "迁出机构存在到期未销账的挂账信息";
        }
    }
    public void B023_GET_CHG_BR_BBR() throws IOException,SQLException,Exception {
        WS_AC_MSTBR_GEN = WS_AC_MSTBR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_AC_MSTBR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        WS_TEMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR != '2') {
            if (BPCPQORG.ATTR == '3') {
                if (BPCPQORG.BBR != 0) {
                    IBS.init(SCCGWA, BPCPQORG);
                    CEP.TRC(SCCGWA, WS_TEMP_BBR);
                    BPCPQORG.BR = WS_TEMP_BBR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                    CEP.TRC(SCCGWA, BPCPQORG.BBR);
                    if (BPCPQORG.ATTR != '2') {
                        CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                    } else {
                        WS_AC_MSTBR_GEN = BPCPQORG.BBR;
                    }
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                }
            } else {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
            }
        }
    }
    public void B020_OUTPUT_NOT_ALLOW_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 264;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZSCVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CVBR", AICSCVBR);
    }
    public void T000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CLIB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CLIB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCLIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void T000_STARTBR_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_BR.rp = new DBParm();
        BPTVHPB_BR.rp.TableName = "BPTVHPB";
        BPTVHPB_BR.rp.where = "BR = :BPRTLVB.KEY.BR "
            + "AND POLL_BOX_IND = '1'";
        IBS.STARTBR(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
    }
    public void T000_READNEXT_BPTVHPB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VHPB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VHPB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVHPB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTVHPB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVHPB_BR);
    }
    public void T000_READ_BPTTBVD_FIRST() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO";
        BPTTBVD_RD.fst = true;
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            TBVD_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TBVD_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTBVD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_STARTBR_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "MOV_STS = '1' "
            + "AND ( IN_BR = :BPRCMOV.IN_BR "
            + "OR OUT_BR = :BPRCMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_READNEXT_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void T000_STARTBR_BPTDMOV() throws IOException,SQLException,Exception {
        BPTDMOV_BR.rp = new DBParm();
        BPTDMOV_BR.rp.TableName = "BPTDMOV";
        BPTDMOV_BR.rp.where = "MOV_STS = '0' "
            + "AND ( IN_BR = :BPRDMOV.IN_BR "
            + "OR OUT_BR = :BPRDMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
    }
    public void T000_READNEXT_BPTDMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRDMOV, this, BPTDMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTDMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_ENDBR_BPTDMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTDMOV_BR);
    }
    public void T000_STARTBR_BPTBMOV() throws IOException,SQLException,Exception {
        BPTBMOV_BR.rp = new DBParm();
        BPTBMOV_BR.rp.TableName = "BPTBMOV";
        BPTBMOV_BR.rp.where = "MOV_STS = '0' "
            + "AND ( IN_BR = :BPRBMOV.IN_BR "
            + "OR OUT_BR = :BPRBMOV.OUT_BR )";
        IBS.STARTBR(SCCGWA, BPRBMOV, this, BPTBMOV_BR);
    }
    public void T000_READNEXT_BPTBMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBMOV, this, BPTBMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BMOV_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BMOV_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBMOV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_ENDBR_BMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBMOV_BR);
    }
    public void T000_STARTBR_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_BR.rp = new DBParm();
        BPTALIB_BR.rp.TableName = "BPTALIB";
        BPTALIB_BR.rp.where = "( APP_BR = :BPRALIB.APP_BR "
            + "OR UP_BR = :BPRALIB.UP_BR ) "
            + "AND ( APP_STS NOT IN ( '2' , '7' , '6' ) )";
        IBS.STARTBR(SCCGWA, BPRALIB, this, BPTALIB_BR);
    }
    public void T000_READNEXT_BPTALIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRALIB, this, BPTALIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            ALIB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            ALIB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_ENDBR_BPTALIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTALIB_BR);
    }
    public void T000_STARTBR_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_BR.rp = new DBParm();
        BPTAPLI_BR.rp.TableName = "BPTAPLI";
        BPTAPLI_BR.rp.where = "( APP_BR = :BPRAPLI.APP_BR "
            + "OR UP_BR = :BPRAPLI.UP_BR ) "
            + "AND ( APP_STS NOT IN ( '2' , '7' , '6' ) )";
        IBS.STARTBR(SCCGWA, BPRAPLI, this, BPTAPLI_BR);
    }
    public void T000_READNEXT_BPTAPLI() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRAPLI, this, BPTAPLI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            APLI_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            APLI_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPLI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_ENDBR_BPTAPLI() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTAPLI_BR);
    }
    public void T000_STARTBR_TLVB() throws IOException,SQLException,Exception {
        BPTTLVB_BR.rp = new DBParm();
        BPTTLVB_BR.rp.TableName = "BPTTLVB";
        BPTTLVB_BR.rp.where = "BR = :BPRTLVB.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
    }
    public void T000_READNEXT_TLVB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTLVB, this, BPTTLVB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            TLVB_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TLVB_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTLVB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLVB_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCHBR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("BP0162")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_CHEBING_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCUCHBR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCHBR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBRCHK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBRCHK=");
            CEP.TRC(SCCGWA, BPCBRCHK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
