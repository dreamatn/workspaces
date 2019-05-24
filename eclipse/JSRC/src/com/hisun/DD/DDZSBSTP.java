package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBSTP {
    brParm BPTLOSS_BR = new brParm();
    brParm DDTCHQ_BR = new brParm();
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String WS_SBSTP_STR_CHQ_NO = " ";
    String WS_SBSTP_END_CHQ_NO = " ";
    char WS_SBSTP_CHQ_TYP = ' ';
    String WS_ERR_MSG = " ";
    long WS_STOP_END_CHQ_NO = 0;
    int WS_LEN = 0;
    short WS_NUMN = 0;
    short WS_NUML = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    int WS_CHQ_CNT = 0;
    int WS_CNT = 0;
    long WS_STR_CHQ_NO9 = 0;
    DDZSBSTP_WS_STOP_DETAIL WS_STOP_DETAIL = new DDZSBSTP_WS_STOP_DETAIL();
    char WS_STOP_FLG = ' ';
    char WS_LOSS_FLG = ' ';
    char WS_CHQ_FLG = ' ';
    DDCOBSTP DDCOBSTP = new DDCOBSTP();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    DDRSTOP DDRSTOP = new DDRSTOP();
    DDRMST DDRMST = new DDRMST();
    BPRLOSS BPRLOSS = new BPRLOSS();
    DDRCHQ DDRCHQ = new DDRCHQ();
    SCCGWA SCCGWA;
    DDCSBSTP DDCSBSTP;
    public void MP(SCCGWA SCCGWA, DDCSBSTP DDCSBSTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBSTP = DDCSBSTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBSTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBSTP.AC_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.INQ_TYP);
        CEP.TRC(SCCGWA, DDCSBSTP.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_MST_PROC();
        if (pgmRtn) return;
        B020_GET_ACAC_INFO();
        if (pgmRtn) return;
        B030_BRW_STP_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSBSTP.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSBSTP.AC_NO;
        CICQACAC.DATA.CCY_ACAC = "156";
        CICQACAC.DATA.CR_FLG = '1';
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B030_BRW_STP_INF_PROC() throws IOException,SQLException,Exception {
        B030_10_OPEN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        if (DDCSBSTP.INQ_TYP == '0') {
            CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCSBSTP.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDCSBSTP.END_CHQ_NO);
            if (DDCSBSTP.CHQ_TYP == ' ') {
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    B030_30_STBR_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0) 
                        || (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0)) {
                        B030_30_STBR_01_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                        B030_30_STBR_02_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (DDCSBSTP.CHQ_TYP != ' ') {
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    B030_30_STBR_03_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0) 
                        || (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0)) {
                        B030_30_STBR_04_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                        B030_30_STBR_05_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            B030_50_READ_NEXT_PROC();
            if (pgmRtn) return;
            B030_60_LOSS_PROC();
            if (pgmRtn) return;
            B030_90_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (DDCSBSTP.INQ_TYP == '1') {
            B030_35_STBR_PROC();
            if (pgmRtn) return;
            B030_50_READ_NEXT_PROC();
            if (pgmRtn) return;
            B030_60_LOSS_PROC();
            if (pgmRtn) return;
            B030_90_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (DDCSBSTP.INQ_TYP == '2') {
            B030_40_STBR_PROC();
            if (pgmRtn) return;
            B030_50_READ_NEXT_PROC();
            if (pgmRtn) return;
            B030_60_LOSS_PROC();
            if (pgmRtn) return;
            B030_90_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (DDCSBSTP.INQ_TYP == '3') {
            CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
            if (DDCSBSTP.CHQ_TYP == ' ') {
                B030_70_STBR_PROC();
                if (pgmRtn) return;
            } else {
                B030_70_STBR_01_PROC();
                if (pgmRtn) return;
            }
            B030_70_READ_NEXT_PROC();
            if (pgmRtn) return;
            B030_70_CHQ_PROC();
            if (pgmRtn) return;
            B030_70_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (DDCSBSTP.INQ_TYP == '4') {
            CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCSBSTP.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDCSBSTP.END_CHQ_NO);
            CEP.TRC(SCCGWA, DDCSBSTP.AC_NO);
            if (DDCSBSTP.CHQ_TYP == ' ') {
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    B020_40_STBR_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0) 
                        || (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0)) {
                        B020_40_STBR_01_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                        B020_40_STBR_02_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (DDCSBSTP.CHQ_TYP != ' ') {
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    B020_40_STBR_03_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0) 
                        || (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() > 0)) {
                        B020_40_STBR_04_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                        && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                        B020_40_STBR_05_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            B030_50_READ_NEXT_PROC();
            if (pgmRtn) return;
            B030_60_LOSS_PROC();
            if (pgmRtn) return;
            B030_90_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSBSTP.INQ_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B030_60_LOSS_PROC() throws IOException,SQLException,Exception {
        while (WS_LOSS_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, "RECORD GET");
            if ((BPRLOSS.LOS_DT != 0 
                && BPRLOSS.LOS_DT <= SCCGWA.COMM_AREA.AC_DATE)) {
                B030_70_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            B030_50_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_70_CHQ_PROC() throws IOException,SQLException,Exception {
        while (WS_CHQ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B030_90_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B030_70_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_10_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 895;
        if ("10".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("10");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_30_STBR_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_30_STBR_01_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        WS_SBSTP_END_CHQ_NO = DDCSBSTP.END_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND BILL_NO <= :WS_SBSTP_END_CHQ_NO "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_30_STBR_02_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_30_STBR_03_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_30_STBR_04_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        WS_SBSTP_END_CHQ_NO = DDCSBSTP.END_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND BILL_NO <= :WS_SBSTP_END_CHQ_NO "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_30_STBR_05_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND STS = '2'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_35_STBR_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND STS = '1'";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_40_STBR_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_01_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        WS_SBSTP_END_CHQ_NO = DDCSBSTP.END_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND BILL_NO <= :WS_SBSTP_END_CHQ_NO "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_02_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_03_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_04_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        WS_SBSTP_END_CHQ_NO = DDCSBSTP.END_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND BILL_NO <= :WS_SBSTP_END_CHQ_NO "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B020_40_STBR_05_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        IBS.init(SCCGWA, BPRLOSS);
        BPRLOSS.AC = DDCSBSTP.AC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        WS_SBSTP_STR_CHQ_NO = DDCSBSTP.STR_CHQ_NO;
        BPTLOSS_BR.rp = new DBParm();
        BPTLOSS_BR.rp.TableName = "BPTLOSS";
        BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
            + "AND BILL_TYP = :WS_SBSTP_CHQ_TYP "
            + "AND BILL_NO >= :WS_SBSTP_STR_CHQ_NO "
            + "AND STS IN ( '1' , '2' )";
        BPTLOSS_BR.rp.order = "LOS_NO,BILL_NO";
        IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
    }
    public void B030_70_STBR_PROC() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP IN ( '1' , '2' , '3' , '5' )";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ END");
    }
    public void B030_70_STBR_01_PROC() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_SBSTP_CHQ_TYP = DDCSBSTP.CHQ_TYP;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :WS_SBSTP_CHQ_TYP";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ01 END");
    }
    public void B030_70_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void B030_50_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LOSS_FLG = 'Y';
        } else {
            WS_LOSS_FLG = 'N';
        }
    }
    public void B030_90_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_NUMN = 0;
        WS_NUMN = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_NUMN);
        if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
        if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
        else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_NUMN));
        if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
        JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
        if (DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_CHQ_END_CHQ_NO = 0;
        else WS_CHQ_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_NUMN));
        WS_CHQ_CNT = (int) (WS_CHQ_END_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
        WS_CNT = 0;
        CEP.TRC(SCCGWA, DDCSBSTP.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
        for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, WS_STOP_DETAIL);
                if (WS_NUMN != 16) {
                    WS_NUML = (short) (16 - WS_NUMN + 1);
                    CEP.TRC(SCCGWA, WS_NUML);
                    JIBS_tmp_str[0] = "" + WS_CHQ_STR_CHQ_NO;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_STOP_DETAIL.WS_STR_CHQ_NO = JIBS_tmp_str[0].substring(WS_NUML - 1, WS_NUML + WS_NUMN - 1);
                } else {
                    WS_STOP_DETAIL.WS_STR_CHQ_NO = "" + WS_CHQ_STR_CHQ_NO;
                    JIBS_tmp_int = WS_STOP_DETAIL.WS_STR_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) WS_STOP_DETAIL.WS_STR_CHQ_NO = "0" + WS_STOP_DETAIL.WS_STR_CHQ_NO;
                }
                WS_CHQ_STR_CHQ_NO += 1;
                WS_STOP_DETAIL.WS_CHQ_TYPE = DDRCHQ.KEY.CHQ_TYP;
                CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_STR_CHQ_NO);
                WS_STOP_DETAIL.WS_BV_CODE = DDRCHQ.CHQ_BV_CD;
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    CEP.TRC(SCCGWA, "BOTH SPACE");
                    B030_90_OUTPUT_PROC();
                    if (pgmRtn) return;
                }
                if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "BOTH NOT SPACE");
                    if (WS_STOP_DETAIL.WS_STR_CHQ_NO.compareTo(DDCSBSTP.STR_CHQ_NO) >= 0 
                        && WS_STOP_DETAIL.WS_STR_CHQ_NO.compareTo(DDCSBSTP.END_CHQ_NO) <= 0) {
                        CEP.TRC(SCCGWA, "DATA IN START TO END");
                        B030_90_OUTPUT_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (DDCSBSTP.STR_CHQ_NO.trim().length() > 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() == 0) {
                    CEP.TRC(SCCGWA, "START NOT SPACE");
                    if (WS_STOP_DETAIL.WS_STR_CHQ_NO.compareTo(DDCSBSTP.STR_CHQ_NO) >= 0) {
                        CEP.TRC(SCCGWA, "GREAT THAN START");
                        B030_90_OUTPUT_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (DDCSBSTP.STR_CHQ_NO.trim().length() == 0 
                    && DDCSBSTP.END_CHQ_NO.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "END NOT SPACE");
                    if (WS_STOP_DETAIL.WS_STR_CHQ_NO.compareTo(DDCSBSTP.END_CHQ_NO) <= 0) {
                        CEP.TRC(SCCGWA, "LESS THAN END");
                        B030_90_OUTPUT_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                WS_CHQ_STR_CHQ_NO += 1;
            }
        }
    }
    public void B030_70_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_STOP_DETAIL.WS_CHQ_TYPE = BPRLOSS.BILL_TYP;
        WS_STOP_DETAIL.WS_STR_CHQ_NO = BPRLOSS.BILL_NO;
        WS_STOP_DETAIL.WS_BV_TYP = BPRLOSS.BV_TYP;
        WS_STOP_DETAIL.WS_BV_CODE = BPRLOSS.BV_CODE;
        WS_STOP_DETAIL.WS_AMT = BPRLOSS.OPEN_AMT;
        WS_STOP_DETAIL.WS_CHQ_DATE = BPRLOSS.OPEN_DT;
        WS_STOP_DETAIL.WS_PAYEE = BPRLOSS.GET_NM;
        WS_STOP_DETAIL.WS_LOST_RSN = BPRLOSS.LOS_RMK;
        WS_STOP_DETAIL.WS_LOST_NAME = BPRLOSS.LOS_NM;
        WS_STOP_DETAIL.WS_PAPER_TYP = BPRLOSS.LOS_ID_TYP;
        WS_STOP_DETAIL.WS_PAPER_NO = BPRLOSS.LOS_ID_NO;
        WS_STOP_DETAIL.WS_W_LOST_DATE = BPRLOSS.LOS_DT;
        WS_STOP_DETAIL.WS_LOST_EXP_DATE = BPRLOSS.RLOS_DUE_TIME;
        if (BPRLOSS.STS == '2') {
            WS_STOP_DETAIL.WS_LOST_TYPE = '3';
        } else {
            if (BPRLOSS.STS == '1') {
                WS_STOP_DETAIL.WS_LOST_TYPE = '4';
            }
        }
        WS_STOP_DETAIL.WS_LOS_NO = BPRLOSS.KEY.LOS_NO;
        WS_STOP_DETAIL.WS_LOS_TELE = BPRLOSS.LOS_TELE;
        WS_STOP_DETAIL.WS_LOS_ADDR = BPRLOSS.LOS_ADDR;
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "LIST OUTPUT:");
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_CHQ_TYPE);
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_W_LOST_DATE);
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_LOST_EXP_DATE);
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_LOST_TYPE);
        CEP.TRC(SCCGWA, WS_STOP_DETAIL.WS_LOS_NO);
        CEP.TRC(SCCGWA, "************");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_STOP_DETAIL);
        SCCMPAG.DATA_LEN = 895;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_70_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void B030_90_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTLOSS_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_90_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_STOP_DETAIL);
        SCCMPAG.DATA_LEN = 895;
        B_MPAG();
        if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
