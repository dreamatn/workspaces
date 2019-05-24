package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class CIOT9980 {
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String CPN_SCSSCKDT = "SCSSCKDT";
    CIOT9980_WS_RC WS_RC = new CIOT9980_WS_RC();
    int WS_I = 0;
    short WS_AP_CODE_N = 0;
    int WS_TM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCRCWA SCRCWA = new SCRCWA();
    SCRCWAT SCRCWAT = new SCRCWAT();
    SCRCWA2 SCRCWA2 = new SCRCWA2();
    SCRAPSTU SCRAPSTU = new SCRAPSTU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9980_AWA_9980 CIB9980_AWA_9980;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9980 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9980_AWA_9980>");
        CIB9980_AWA_9980 = (CIB9980_AWA_9980) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_WRITE_AND_CHECK();
        if (pgmRtn) return;
    }
    public void B010_WRITE_AND_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRCWA);
        SCTCWA_BR.rp = new DBParm();
        SCTCWA_BR.rp.TableName = "SCTCWA";
        IBS.STARTBR(SCCGWA, SCRCWA, SCTCWA_BR);
        IBS.READNEXT(SCCGWA, SCRCWA, this, SCTCWA_BR);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, SCRCWA.KEY.BANK_NO);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRCWAT);
            IBS.init(SCCGWA, SCRCWA2);
            SCRCWA2.KEY.BANK_NO = SCRCWAT.KEY.BANK_NO;
            SCRCWA2.VERSION_NO = SCRCWAT.VERSION_NO;
            SCRCWA2.SYS_ID = SCRCWAT.SYS_ID;
            SCRCWA2.SYS_DEST = SCRCWAT.SYS_DEST;
            SCRCWA2.SYS_STUS = SCRCWAT.SYS_STUS;
